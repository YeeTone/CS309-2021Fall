function onClickAddHotel() {
    addRow();
}

function setDistrict() {
    let guangzhou = document.getElementsByName("city")[0];
    let shenzhen = document.getElementsByName("city")[1];

    document.getElementById("district").length = 0;
    document.getElementById("district").appendChild(new Option("--", null));
    gz = ["LI WAN", "YUE XIU", "HAI ZHU", "TIAN HE", "BAI YUN", "HUANG PU", "PAN YU", "HUA DU", "NAN SHA", "CONG HUA", "ZENG CHENG"];
    sz = ["FU TIAN", "NAN SHAN", "LUO HU", "LONG GANG", "PING SHAN", "LONG HUA", "GUANG MING", "YAN", "TIAN"];

    if (shenzhen.checked) {
        for (let i = 0; i < sz.length; i++) {
            document.getElementById("district").appendChild(new Option(sz[i], sz[i]));
        }
        //document.getElementById("district").appendChild(new Option("SY","SY"));
    } else if (guangzhou.checked) {
        for (let i = 0; i < sz.length; i++) {
            document.getElementById("district").appendChild(new Option(gz[i], gz[i]));
        }
    }
}

function addRow(){
    let bodyObj = document.getElementById("tbody");
    if(!bodyObj){
        alert("Body of Table is not exist!");
        return;
    }

    bodyObj.rows[0].style.display = "none";

    let rowCount = bodyObj.rows.length;
    let cellCount = bodyObj.rows[0].cells.length;
    bodyObj.style.display = "";

    if(!checkName()){
        alert("The hotel name is not valid!");
        return;
    }

    let guangzhou = document.getElementsByName("city")[0];
    let shenzhen = document.getElementsByName("city")[1];
    if(!guangzhou.checked && !shenzhen.checked){
        alert("City is not input!");
        return;
    }

    if(!checkDistrict()) {
        alert("District is not input");
        return;
    }
    if(!checkDate()){
        alert("Date is not valid!");
        return;
    }
    if(!checkPrice()){
        alert("Price should be unique!")
        return;
    }

    let newRow = bodyObj.insertRow(rowCount ++);

    newRow.insertCell(0).innerHTML = document.getElementsByName("hotel-name")[0].value;
    if(guangzhou.checked){
        newRow.insertCell(1).innerHTML = "GUANG ZHOU";
    }else{
        newRow.insertCell(1).innerHTML = "SHEN ZHEN";
    }

    newRow.insertCell(2).innerHTML = document.getElementById("district").value;
    newRow.insertCell(3).innerHTML = document.getElementById("date1").value;
    newRow.insertCell(4).innerHTML = document.getElementById("time").value;
    newRow.insertCell(5).innerHTML = document.getElementById("price").value;
    newRow.insertCell(6).innerHTML = document.getElementById("roomType1").value;
    newRow.insertCell(7).innerHTML = bodyObj.rows[0].cells[cellCount - 1].innerHTML;
}

function removeRow(inputobj) {
    if (!inputobj) return;
    let parentTD = inputobj.parentNode;
    let parentTR = parentTD.parentNode;
    let parentTBODY = parentTR.parentNode;
    parentTBODY.removeChild(parentTR);
}

function setDisplay(){
    let form = document.getElementById("form1");
    let photos = document.getElementById("photos");
    if(form.style.display === "none"){
        form.style.display = "";
        photos.style.display = "none";
    }else {
        form.style.display = "none";
        photos.style.display = "";
    }

}

function setDate(){
    let date = new Date();
    date.setDate(date.getDate() + 1)
    let year = date.getFullYear();
    let month = ('0'+(date.getMonth() + 1)).slice(-2);
    let day = ('0'+(date.getDate())).slice(-2);

    document.getElementById("date1").min=year + '-' + month +'-'+ day;
}

function hero() {
    var herowidth=1920;//改为你要的网页宽度
    var heroheight=1080;//改为你要的网页高度
    window.resizeTo(herowidth,heroheight);
}

function checkName(){
    let name = document.getElementById("hotel-name").value;
    return /^[a-zA-Z]+$/.test(name);
}

function checkDistrict(){
    return document.getElementById("district").value !== null;
}

function checkDate(){
    let arr = [];
    let date1 = document.getElementById("date1").value;
    if(date1 === null){
        return false;
    }

    arr = date1.split('-');
    let cDate = new Date(arr[0],parseInt(arr[1]),parseInt(arr[2])).getTime();
    let date2 = new Date().getTime();
    return cDate >= date2;
}

function checkPrice(){
    let hotel_name = document.getElementById("hotel-name").value;

    let district = document.getElementById("district").value;

    let price = document.getElementById("price").value;
    if(!/^[0-9]+$/.test(price)){
        return false;
    }

    let roomType = document.getElementById("roomType1").value;

    let guangzhou = document.getElementsByName("city")[0];

    const result = guangzhou.checked ? "GUANG ZHOU" : "SHEN ZHEN";

    let bodyObj = document.getElementById("tbody");

    let rowCount= bodyObj.rows.length,columnCount = bodyObj.rows[0].cells.length;

    for (let i = 0;i < rowCount; i++){
        let hn = bodyObj.rows[i].cells[0].innerHTML;
        let ci = bodyObj.rows[i].cells[1].innerHTML;
        let di = bodyObj.rows[i].cells[2].innerHTML;
        let pr = bodyObj.rows[i].cells[5].innerHTML;
        let rt = bodyObj.rows[i].cells[6].innerHTML;

        //alert(ci.toString()+" "+result.toString())

        if(hn.toString() === hotel_name.toString()
            && ci.toString() === result.toString()
            && di.toString() === district.toString()
            && rt !== roomType){
            return pr !== price;
        }
    }

    return true;

}

function clickEffect() {
    let balls = [];
    let longPressed = false;
    let longPress;
    let multiplier = 0;
    let width, height;
    let origin;
    let normal;
    let ctx;
    const colours = ["#F73859", "#14FFEC", "#00E0FF", "#FF99FE", "#FAF15D"];
    const canvas = document.createElement("canvas");
    document.body.appendChild(canvas);
    canvas.setAttribute("style", "width: 100%; height: 100%; top: 0; left: 0; z-index: 99999; position: fixed; pointer-events: none;");
    const pointer = document.createElement("span");
    pointer.classList.add("pointer");
    document.body.appendChild(pointer);

    if (canvas.getContext && window.addEventListener) {
        ctx = canvas.getContext("2d");
        updateSize();
        window.addEventListener('resize', updateSize, false);
        loop();
        window.addEventListener("mousedown", function(e) {
            pushBalls(randBetween(10, 20), e.clientX, e.clientY);
            document.body.classList.add("is-pressed");
            longPress = setTimeout(function() {
                document.body.classList.add("is-longpress");
                longPressed = true;
            }, 500);
        }, false);
        window.addEventListener("mouseup", function(e) {
            clearInterval(longPress);
            if (longPressed == true) {
                document.body.classList.remove("is-longpress");
                pushBalls(randBetween(50 + Math.ceil(multiplier), 100 + Math.ceil(multiplier)), e.clientX, e.clientY);
                longPressed = false;
            }
            document.body.classList.remove("is-pressed");
        }, false);
        window.addEventListener("mousemove", function(e) {
            let x = e.clientX;
            let y = e.clientY;
            pointer.style.top = y + "px";
            pointer.style.left = x + "px";
        }, false);
    } else {
        console.log("canvas or addEventListener is unsupported!");
    }


    function updateSize() {
        canvas.width = window.innerWidth * 2;
        canvas.height = window.innerHeight * 2;
        canvas.style.width = window.innerWidth + 'px';
        canvas.style.height = window.innerHeight + 'px';
        ctx.scale(2, 2);
        width = (canvas.width = window.innerWidth);
        height = (canvas.height = window.innerHeight);
        origin = {
            x: width / 2,
            y: height / 2
        };
        normal = {
            x: width / 2,
            y: height / 2
        };
    }
    class Ball {
        constructor(x = origin.x, y = origin.y) {
            this.x = x;
            this.y = y;
            this.angle = Math.PI * 2 * Math.random();
            if (longPressed === true) {
                this.multiplier = randBetween(14 + multiplier, 15 + multiplier);
            } else {
                this.multiplier = randBetween(6, 12);
            }
            this.vx = (this.multiplier + Math.random() * 0.5) * Math.cos(this.angle);
            this.vy = (this.multiplier + Math.random() * 0.5) * Math.sin(this.angle);
            this.r = randBetween(8, 12) + 3 * Math.random();
            this.color = colours[Math.floor(Math.random() * colours.length)];
        }
        update() {
            this.x += this.vx - normal.x;
            this.y += this.vy - normal.y;
            normal.x = -2 / window.innerWidth * Math.sin(this.angle);
            normal.y = -2 / window.innerHeight * Math.cos(this.angle);
            this.r -= 0.3;
            this.vx *= 0.9;
            this.vy *= 0.9;
        }
    }

    function pushBalls(count = 1, x = origin.x, y = origin.y) {
        for (let i = 0; i < count; i++) {
            balls.push(new Ball(x, y));
        }
    }

    function randBetween(min, max) {
        return Math.floor(Math.random() * max) + min;
    }

    function loop() {
        ctx.fillStyle = "rgba(255, 255, 255, 0)";
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        for (let i = 0; i < balls.length; i++) {
            let b = balls[i];
            if (b.r < 0) continue;
            ctx.fillStyle = b.color;
            ctx.beginPath();
            ctx.arc(b.x, b.y, b.r, 0, Math.PI * 2, false);
            ctx.fill();
            b.update();
        }
        if (longPressed === true) {
            multiplier += 0.2;
        } else if (!longPressed && multiplier >= 0) {
            multiplier -= 0.4;
        }
        removeBall();
        requestAnimationFrame(loop);
    }

    function removeBall() {
        for (let i = 0; i < balls.length; i++) {
            let b = balls[i];
            if (b.x + b.r < 0 || b.x - b.r > width || b.y + b.r < 0 || b.y - b.r > height || b.r < 0) {
                balls.splice(i, 1);
            }
        }
    }
}