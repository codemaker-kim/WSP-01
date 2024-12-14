let mapX = 49;
let mapY = 49;
let mapZ = 20;


document.getElementById('uploadButton').addEventListener('click', async () =>{
   const tile = document.getElementById('uploadTile');
   console.log("click!");
   if(!tile.files.length){
       alert("파일을 업로드 해주세요");
   }

   const formData = new FormData();
   formData.append('tiles', tile.files[0]);

    const alertWindow = document.createElement('div');
    alertWindow.id = 'alertWindow';
    alertWindow.style.position = 'fixed';
    alertWindow.style.top = '50%';
    alertWindow.style.left = '50%';
    alertWindow.style.transform = 'translate(-50%, -50%)';
    alertWindow.style.padding = '20px';
    alertWindow.style.backgroundColor = '#f8d7da';
    alertWindow.style.color = '#721c24';
    alertWindow.style.border = '1px solid #f5c6cb';
    alertWindow.style.borderRadius = '5px';
    alertWindow.style.boxShadow = '0 2px 10px rgba(0, 0, 0, 0.1)';
    alertWindow.textContent = '파일 업로드 중..(시간이 꽤 오래걸립니다. 기다려주세요!)';
    document.body.appendChild(alertWindow);

   try{
       const response = await fetch('/uploadTile', {
           method: 'POST',
           body: formData,
       })

       if(!response.ok){
           throw new Error("업로드 실패");
       }
       else if(response.ok)
           setMap(mapX, mapY);

   } catch(e) {
       console.error('Error!: ', e);
   } finally{
       document.body.removeChild(alertWindow);
   }
});

function setMap(x, y) {
    const tileSize = mapZ * 2;
    fetch('/tiles', {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(imageUrls => {
            const container = document.getElementById('map-area');
            container.innerHTML = '';

            imageUrls.sort((a, b) => {
                const matchA = a.match(/tile_(\d+)_(\d+)\.png/);
                const matchB = b.match(/tile_(\d+)_(\d+)\.png/);

                if (matchA && matchB) {
                    const rowA = parseInt(matchA[1], 10); // 첫 번째 숫자 (row)
                    const rowB = parseInt(matchB[1], 10);

                    const colA = parseInt(matchA[2], 10); // 두 번째 숫자 (column)
                    const colB = parseInt(matchB[2], 10);

                    // 먼저 row 기준으로 정렬
                    if (rowA !== rowB) {
                        return rowA - rowB;
                    }

                    // row가 같으면 column 기준으로 정렬
                    return colA - colB;
                }

                // 파일 이름 패턴이 일치하지 않으면 정렬하지 않음
                return 0;
            });


            container.innerHTML = '';
            let previousX = null; // 이전 X 좌표를 저장할 변수

            imageUrls.forEach(url => {
                const img = document.createElement('img');
                const xyMatch = url.match(/tile_(\d+)_(\d+)\.png/);

                if (xyMatch) {
                    const miniX = parseInt(xyMatch[1], 10);
                    const miniY = parseInt(xyMatch[2], 10);

                    if (
                        miniX >= mapX - mapZ / 2 && miniX < mapX + mapZ / 2 &&
                        miniY >= mapY - mapZ / 2 && miniY < mapY + mapZ / 2
                    ) {
                        // x 값이 변경되었으면 줄바꿈
                        if (previousX !== null && miniX !== previousX) {
                            const lineBreak = document.createElement('br');
                            container.appendChild(lineBreak);
                        }
                        previousX = miniX; // 현재 X 좌표를 업데이트

                        img.src = url;
                        img.alt = 'Dynamic Image';

                        img.style.width = `${tileSize}px`;
                        img.style.height = `${tileSize}px`;
                        img.style.left = `${(miniX - mapX) * tileSize}px`;
                        img.style.top = `${(miniY - mapY) * tileSize}px`;

                        container.appendChild(img);
                    }
                }
            });
            const buttons = [
                { className: "btn top", text: "위로", id: "top"},
                { className: "btn bottom", text: "아래로" , id: "bottom"},
                { className: "btn left", text: "왼쪽", id: "left" },
                { className: "btn right", text: "오른쪽" , id: "right"},
            ];

            buttons.forEach(buttonInfo => {
                const button = document.createElement('button');
                button.className = buttonInfo.className;
                button.textContent = buttonInfo.text; // 버튼 텍스트 설정
                button.id = buttonInfo.id;
                container.appendChild(button); // 컨테이너에 추가

                // 이벤트 리스너 추가
                button.addEventListener('click', async () => {
                    switch (button.id) {
                        case 'top':
                            if (mapY < 100 && 0 < mapY && mapZ !== 100) {
                                mapY += 5;
                                setMap(mapX, mapY);
                            }
                            break;
                        case 'bottom':
                            if (mapY > 0 && mapZ !== 100) {
                                mapY -= 5;
                                setMap(mapX, mapY);
                            }
                            break;
                        case 'left':
                            if (mapX > 0 && mapZ !== 100) {
                                mapX -= 5;
                                setMap(mapX, mapY);
                            }
                            break;
                        case 'right':
                            if (mapX < 100 && mapZ !== 100) {
                                mapX += 5;
                                setMap(mapX, mapY);
                            }
                            break;
                    }
                });
            });
            enableMapInteractions();
        })
        .catch(error => {
            console.error('Error fetching images:', error);
        });
}

// 이벤트 리스너를 활성화하는 함수
function findNearestZoomLevel(deltaY) {
    const allowedZoomLevels = [20, 40, 60, 80, 100];
    const index = allowedZoomLevels.indexOf(mapZ);

    if (deltaY < 0 && index < allowedZoomLevels.length - 1) {
        return allowedZoomLevels[index + 1];
    }

    if (deltaY > 0 && index > 0) {
        return allowedZoomLevels[index - 1];
    }

    return mapZ;
}

function enableMapInteractions() {
    const mapArea = document.getElementById('map-area');

    mapArea.addEventListener('wheel', (event) => {
        event.preventDefault();

        const newZoom = findNearestZoomLevel(event.deltaY);

        if (newZoom !== mapZ) {
            mapZ = newZoom;
            console.log("New zoom level:", mapZ);
            setMap(mapX, mapY);
        }
    });
}

/*document.getElementById('load').addEventListener('click', async () =>{
    setMap(mapX, mapY);
});*/
