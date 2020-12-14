$(function () {
    if (WEBGL.isWebGLAvailable() === false) {
        document.body.appendChild(WEBGL.getWebGLErrorMessage());
    } else {
        //$("#canvas").height($(window).outerHeight(true))    //如果canvas有设置大小删除此句
        threeStart();
    }

    // $("#btn").click(chkgun)                         //可以删除
    // $("#btn1").click(chagePower)                    //该按钮事件可以删除

})
function GetQueryString(name) {
				var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
				var r = window.location.search.substr(1).match(reg);
				if (r != null) return unescape(r[2]);
				return null;}
var stationID=GetQueryString('stationID');
var num=1;

var chksta = 0; // 是选枪模式还是正常模式
var selectgun = null;//浮起充电桩选择的枪

var renderer;   //渲染器
var stats;      //fps状态
var scene;      //场景
var camera;     //相机
var directionalLight, ambient;  //灯光
var keyboard;   //键盘控制
var groupmodel1, groupmodel2, groupmodel4;   //枪模型


//Logo颜色渐变
var logo;
var logcolorr = 30;
var logcolorg = 50;
var logcolorb = 70;
var stepr = 1, stepg = 2, stepb = 3;
//系统字体
var sysfont;
//精灵选择背景
var chk0material, chk1material;

//底板点击对象
var clickObjects = [];
//精灵
var clickSprite = []
//正常材质
var readymaterial = new THREE.MeshLambertMaterial({color: 0x2acf80, transparent: true, opacity: 1});     //0准备
var ordermaterial = new THREE.MeshLambertMaterial({color: 0x4ab4fe, transparent: true, opacity: 1});    //1预约
var powermaterial = new THREE.MeshLambertMaterial({color: 0xf6ba40, transparent: true, opacity: 1});    //2充电
var endmaterial = new THREE.MeshLambertMaterial({color: 0x3ae4fb, transparent: true, opacity: 1});      //3充满
var freematerial = new THREE.MeshLambertMaterial({color: 0x0d5a50, transparent: true, opacity: 1});      //4空闲
var warnmaterial = new THREE.MeshLambertMaterial({color: 0xf84143, transparent: true, opacity: 1});      //5告警
var unlinkmaterial = new THREE.MeshLambertMaterial({color: 0x7f8085, transparent: true, opacity: 1});    //6离线
var boxmaterial = new THREE.MeshLambertMaterial({color: 0x107132, transparent: true, opacity: 0.7});      //底部模型
var sysfontMaterial = new THREE.MeshLambertMaterial({
    color: 0xffffff,
    side: THREE.DoubleSide,
    transparent: true,
    opacity: 1
});//字体材质

//透明材质
var readymaterial1 = new THREE.MeshLambertMaterial({color: 0x2acf80, transparent: true, opacity: 1});     //0准备
var ordermaterial1 = new THREE.MeshLambertMaterial({color: 0x4ab4fe, transparent: true, opacity: 1});    //1预约
var powermaterial1 = new THREE.MeshLambertMaterial({color: 0xf6ba40, transparent: true, opacity: 1});    //2充电
var endmaterial1 = new THREE.MeshLambertMaterial({color: 0x3ae4fb, transparent: true, opacity: 1});      //3充满
var freematerial1 = new THREE.MeshLambertMaterial({color: 0x0d5a50, transparent: true, opacity: 1});      //4空闲
var warnmaterial1 = new THREE.MeshLambertMaterial({color: 0xf84143, transparent: true, opacity: 1});      //5告警
var unlinkmaterial1 = new THREE.MeshLambertMaterial({color: 0x7f8085, transparent: true, opacity: 1});    //6离线
var boxmaterial1 = new THREE.MeshLambertMaterial({color: 0x107132, transparent: true, opacity: 0.7});      //底部模型
var sysfontMaterial1 = new THREE.MeshLambertMaterial({
    color: 0xffffff,
    side: THREE.DoubleSide,
    transparent: true,
    opacity: 1
});//字体材质

//透明材质2
var readymaterial2 = new THREE.MeshLambertMaterial({color: 0x2acf80, transparent: true, opacity: 1});     //0准备
var ordermaterial2 = new THREE.MeshLambertMaterial({color: 0x4ab4fe, transparent: true, opacity: 1});    //1预约
var powermaterial2 = new THREE.MeshLambertMaterial({color: 0xf6ba40, transparent: true, opacity: 1});    //2充电
var endmaterial2 = new THREE.MeshLambertMaterial({color: 0x3ae4fb, transparent: true, opacity: 1});      //3充满
var freematerial2 = new THREE.MeshLambertMaterial({color: 0x0d5a50, transparent: true, opacity: 1});      //4空闲
var warnmaterial2 = new THREE.MeshLambertMaterial({color: 0xf84143, transparent: true, opacity: 1});      //5告警
var unlinkmaterial2 = new THREE.MeshLambertMaterial({color: 0x7f8085, transparent: true, opacity: 1});    //6离线
var boxmaterial2 = new THREE.MeshLambertMaterial({color: 0x107132, transparent: true, opacity: 0.7});      //底部模型
var sysfontMaterial2 = new THREE.MeshLambertMaterial({
    color: 0xffffff,
    side: THREE.DoubleSide,
    transparent: true,
    opacity: 1
});//字体材质

//判断鼠标移动还是点击
var mousex = 0, mousey = 0;

//开始加载
function threeStart() {
    initThree();
    // initObject();
    animation();
    addlogo()
    initThreeClickEvent()
}

//初始化场景
function initThree() {

    var candiv = $("#canvas"),
        width = candiv.outerWidth(true),
        height = candiv.outerHeight(true)

    //渲染
    renderer = new THREE.WebGLRenderer({antialias: true, alpha: true});
    renderer.setSize(width, height);
    renderer.setClearColor(0xffffff, 0);
    candiv.append(renderer.domElement)

    //状态
    /*stats = new Stats();
    $(stats.domElement).css({"position": "absolute", "left": 0, "top": 0})
    candiv.append(stats.domElement);
*/
    //场景
    scene = new THREE.Scene();

    //相机
    camera = new THREE.PerspectiveCamera(55, width / height, 1, 10000);
    camera.position.set(0, 1000, 750);
    camera.lookAt(0, 0, 0);
    scene.add(camera);

    //事件
    //THREEx.WindowResize(renderer, camera);
    THREEx.FullScreen.bindKey({charCode: 'm'.charCodeAt(0)});

    //控制器
    controls = new THREE.OrbitControls(camera, renderer.domElement);
    controls.maxPolarAngle = Math.PI * 3 / 7; // radians

    //光源
    directionalLight = new THREE.DirectionalLight(0xffffff, 0.8);//模拟远处类似太阳的光源
    directionalLight.position.set(0, 10, 0).normalize();
    directionalLight.castShadow = true;
    scene.add(directionalLight);

    ambient = new THREE.AmbientLight(0xffffff, 1); //AmbientLight,影响整个场景的光源
    ambient.position.set(0, 0, 0);
    scene.add(ambient);

    keyboard = new THREEx.KeyboardState();

    //组model
    groupmodel1 = groupmodel1();
    groupmodel2 = groupmodel2();
    groupmodel4 = groupmodel4()

}

//循环渲染动画
function animation() {
    if (logo != null) {

        logcolorr = logcolorr + stepr
        logcolorg = logcolorg + stepg
        logcolorb = logcolorb + stepb
        if (logcolorr > 255) {
            logcolorr = logcolorr - 2;
            stepr = -1
        } else if (logcolorr < 0) {
            logcolorr = logcolorr + 2;
            stepr = 1
        }

        if (logcolorg > 255) {
            logcolorg = logcolorg - 3;
            stepg = -2
        } else if (logcolorg < 0) {
            logcolorg = logcolorg + 3;
            stepg = 2
        }

        if (logcolorb > 255) {
            logcolorb = logcolorb - 4;
            stepb = -3
        } else if (logcolorb < 0) {
            logcolorb = logcolorb + 4;
            stepb = 3
        }

        logo.material = new THREE.MeshPhongMaterial({
            color: "rgb(" + logcolorr + ", " + logcolorg + ", " + logcolorb + ")",
            specular: "rgb(" + logcolorr + ", " + logcolorg + ", " + logcolorb + ")",
            shininess: 10,
            transparent: true,
            opacity: 0.4
        });
    }
    TWEEN.update();
    renderer.render(scene, camera);
    requestAnimationFrame(animation);
    // stats.update();
}

//添加点击事件
function initThreeClickEvent() {

    var raycaster = new THREE.Raycaster();
    var mouse = new THREE.Vector2();
    $("#canvas").mousedown(function (event) {
        mousex = (event.offsetX / renderer.domElement.clientWidth) * 2 - 1;
        mousey = -(event.clientY / renderer.domElement.clientHeight) * 2 + 1;
    }).click(onDocumentMouseDown)


    function onDocumentMouseDown(event) {
        event.preventDefault();

        mouse.x = (event.offsetX / renderer.domElement.clientWidth) * 2 - 1;
        mouse.y = -(event.offsetY / renderer.domElement.clientHeight) * 2 + 1;

        if (mousex != mouse.x && mousey != mouse.y) {
            return
        }
        raycaster.setFromCamera(mouse, camera);
        var intersects = raycaster.intersectObjects(clickObjects.concat(clickSprite));
        if (intersects.length > 0) {
            var obj = intersects[0].object
            var parentobj = obj.parent
            var index = parentobj.name

            if (chksta == 0) {
                popPile(gunlist[index]["pileid"])
            } else {
                if (obj.type == "Sprite") {
                    if (obj.material == chk0material) { //选中
                        obj.material = chk1material
                        //获取选中下标
                        var selindex = -1;
                        for (var i = 0; i < selectgun.length; i++) {
                            if (selectgun[i]["index"] == index) {
                                selindex = i;
                                break;
                            }
                        }
                        if (selindex == -1) {
                            selectgun.push({"index": index, "gunNo": [obj.name]})
                        } else {
                            if (selectgun[selindex]["gunNo"].indexOf(obj.name) == -1) {
                                selectgun[selindex]["gunNo"].push(obj.name)
                            }
                        }

                    } else if (obj.material == chk1material) {   //取消选中
                        obj.material = chk0material
                        var selindex = -1;
                        for (var i = 0; i < selectgun.length; i++) {
                            if (selectgun[i]["index"] == index) {
                                selindex = i;
                                break;
                            }
                        }
                        if (selindex > -1) {
                            if (selectgun[selindex]["gunNo"].indexOf(obj.name) == -1) { //本来就不存在
                                if (selectgun[selindex]["gunNo"].length == 0) { //如果该桩的枪为空，移除该数组
                                    selectgun.splice(selindex, 1);
                                }
                            } else {
                                selectgun[selindex]["gunNo"].forEach(function (item, index, arr) {
                                    if (item == obj.name) {
                                        arr.splice(index, 1);
                                    }
                                });

                                if (selectgun[selindex]["gunNo"].length == 0) { //如果该桩的枪为空，移除该数组
                                    selectgun.splice(selindex, 1);
                                }
                            }
                        }


                    }
                    //获取选中的
                    var tmpnum = 0;
                    for (var i = 0; i < selectgun.length; i++) {
                        tmpnum += selectgun[i]["gunNo"].length
                    }
                    $("#checkedimg").text(tmpnum)

                } else {
                    if (parentobj.position.y > 0) {  //弹起->降落
                        var selindex = -1;
                        //更新选枪数据
                        for (var i = 0; i < selectgun.length; i++) {
                            if (selectgun[i]["index"] == index) {
                                selectgun.splice(i, 1);
                            }
                        }
                        readymaterial2.opacity = ordermaterial2.opacity = powermaterial2.opacity = endmaterial2.opacity = freematerial2.opacity = warnmaterial2.opacity = unlinkmaterial2.opacity = sysfontMaterial2.opacity = 1
                        boxmaterial2.opacity = 0.7
                        //清除精灵
                        for (var j = 0; j < parentobj.children.length; j++) {
                            if (parentobj.children[j].type == "Sprite") {
                                var obj11 = parentobj.children[j];
                                clickSprite.forEach(function (item, index, arr) {
                                    if (item == obj11) {
                                        arr.splice(index, 1);
                                    }
                                });
                                parentobj.remove(obj11)
                                scene.remove(obj11)
                                j--
                            }
                        }
                        var gundata = gunlist[index]["gunNo"]
                        var gungunnum = gundata.length;

                        for (var j = 0; j < gungunnum; j++) {
                            parentobj.children[j].material = getMaterial2(gundata[j])
                            parentobj.children[gungunnum + 3 + j].material = sysfontMaterial2
                        }

                        parentobj.children[gungunnum].material = boxmaterial2
                        parentobj.children[gungunnum + 2].material = sysfontMaterial2

                        new TWEEN.Tween(parentobj.position).to({y: 0}, 200).start();
                        new TWEEN.Tween(readymaterial2).to({opacity: 0.3}, 300).start();
                        new TWEEN.Tween(ordermaterial2).to({opacity: 0.3}, 300).start();
                        new TWEEN.Tween(powermaterial2).to({opacity: 0.3}, 300).start();
                        new TWEEN.Tween(endmaterial2).to({opacity: 0.3}, 300).start();
                        new TWEEN.Tween(freematerial2).to({opacity: 0.3}, 300).start();
                        new TWEEN.Tween(warnmaterial2).to({opacity: 0.3}, 300).start();
                        new TWEEN.Tween(unlinkmaterial2).to({opacity: 0.3}, 300).start();
                        new TWEEN.Tween(sysfontMaterial2).to({opacity: 0.3}, 300).start();
                        new TWEEN.Tween(boxmaterial2).to({opacity: 0.2}, 300).start().onComplete(function () {
                            for (var j = 0; j < gungunnum; j++) {
                                parentobj.children[j].material = getMaterial1(gundata[j])
                                parentobj.children[gungunnum + 3 + j].material = sysfontMaterial1
                            }
                            parentobj.children[gungunnum].material = boxmaterial1
                            parentobj.children[gungunnum + 2].material = sysfontMaterial1
                        });
                        //获取选中的
                        var tmpnum = 0;
                        for (var i = 0; i < selectgun.length; i++) {
                            tmpnum += selectgun[i]["gunNo"].length
                        }
                        $("#checkedimg").text(tmpnum)
                    } else {

                        var gundata = gunlist[index]["gunNo"]
                        var gungunnum = gundata.length;
                        var sta = 0
                        for (var j = 0; j < gungunnum; j++) {
                            if (gundata[j] == 0) {
                                sta = 1;
                                var sprite = new THREE.Sprite(chk0material)
                                sprite.name = j;
                                sprite.scale.set(15, 15, 15)
                                sprite.position.x = 20
                                sprite.position.y = 68
                                if (gungunnum == 1) {

                                } else if (gungunnum == 2) {
                                    if (j == 0) {
                                        sprite.position.z = -22
                                    } else if (j == 1) {
                                        sprite.position.z = 22
                                    }
                                } else if (gungunnum == 4) {
                                    if (j == 0) {
                                        sprite.position.z = -45
                                    } else if (j == 1) {
                                        sprite.position.z = -15
                                    } else if (j == 2) {
                                        sprite.position.z = 15
                                    } else if (j == 3) {
                                        sprite.position.z = 45
                                    }
                                }
                                parentobj.add(sprite)
                                clickSprite.push(sprite)
                            }
                        }
                        if (sta == 1) {
                            readymaterial2.opacity = ordermaterial2.opacity = powermaterial2.opacity = endmaterial2.opacity = freematerial2.opacity = warnmaterial2.opacity = unlinkmaterial2.opacity = sysfontMaterial2.opacity = 0.3
                            boxmaterial2.opacity = 0.2
                            for (var j = 0; j < gungunnum; j++) {
                                parentobj.children[j].material = getMaterial2(gundata[j])
                                parentobj.children[gungunnum + 3 + j].material = sysfontMaterial2
                            }

                            parentobj.children[gungunnum].material = boxmaterial2
                            parentobj.children[gungunnum + 2].material = sysfontMaterial2

                            new TWEEN.Tween(parentobj.position).to({y: 20}, 200).start();
                            new TWEEN.Tween(readymaterial2).to({opacity: 1}, 300).start();
                            new TWEEN.Tween(ordermaterial2).to({opacity: 1}, 300).start();
                            new TWEEN.Tween(powermaterial2).to({opacity: 1}, 300).start();
                            new TWEEN.Tween(endmaterial2).to({opacity: 1}, 300).start();
                            new TWEEN.Tween(freematerial2).to({opacity: 1}, 300).start();
                            new TWEEN.Tween(warnmaterial2).to({opacity: 1}, 300).start();
                            new TWEEN.Tween(unlinkmaterial2).to({opacity: 1}, 300).start();
                            new TWEEN.Tween(sysfontMaterial2).to({opacity: 1}, 300).start();
                            new TWEEN.Tween(boxmaterial2).to({opacity: 0.7}, 300).start().onComplete(function () {
                                for (var j = 0; j < gungunnum; j++) {
                                    parentobj.children[j].material = getMaterial(gundata[j])
                                    parentobj.children[gungunnum + 3 + j].material = sysfontMaterial
                                }
                                parentobj.children[gungunnum].material = boxmaterial
                                parentobj.children[gungunnum + 2].material = sysfontMaterial
                            });
                        }
                        //获取选中的
                        var tmpnum = 0;
                        for (var i = 0; i < selectgun.length; i++) {
                            tmpnum += selectgun[i]["gunNo"].length
                        }
                        $("#checkedimg").text(tmpnum)
                    }
                }

            }

        }


    }

}

//添加地板
function initObject() {

    var floorGeometry = new THREE.BoxGeometry(mapsize["width"], mapsize["height"], 5);
    var floorMaterial = new THREE.MeshLambertMaterial({
        color: 0xffffff,
        specular: 0xffffff,
        shininess: 10,
        transparent: true,
        opacity: 0.2
    });
    var floor = new THREE.Mesh(floorGeometry, floorMaterial);
    floor.position.y = -3;
    floor.rotation.x = Math.PI / 2;
    scene.add(floor);
    var border = new THREE.BoxHelper(floor, 0x6eaef6);//设置边框
    scene.add(border);

}

//添加网格
function initGrid() {
    var wh = mapsize["width"] > mapsize["height"] ? mapsize["height"] : mapsize["width"];
    var helper = new THREE.GridHelper(wh - 100, parseInt((wh - 100) / 32), 0xff3333, 0x6eaef6);
    scene.add(helper);
}

//添加logo和网络材质
function addlogo() {
    /* var sphereGeometry = new THREE.BoxGeometry(150, 60, 10);
     var cubeMaterial = new THREE.MeshPhongMaterial({
         color: "rgb(" + logcolorr + ", " + logcolorg + ", " + logcolorb + ")",
         specular: "rgb(" + logcolorr + ", " + logcolorg + ", " + logcolorb + ")",
         shininess: 10,
         transparent: true,
         opacity: 0.4
     });
     var fontMaterial = new THREE.MeshLambertMaterial({
         color: 0xffffff,
         side: THREE.DoubleSide
     });
     logo = new THREE.Mesh(sphereGeometry, cubeMaterial);
     logo.position.y = 30
     logo.position.z = -525
     scene.add(logo);


     var loader = new THREE.FontLoader();
     loader.load('3djs/STZhongsong.json', function (font) {
         var shapes = font.generateShapes("凝智科技", 14);
         var fontGeometry = new THREE.ShapeGeometry(shapes);

         // 绑定盒子模型
         fontGeometry.computeBoundingBox();
         var font = new THREE.Mesh(fontGeometry, fontMaterial);
         font.position.y = 22
         font.position.x = -38
         font.position.z = -525
         scene.add(font);
     })
 */
    var loader = new THREE.FontLoader();
    loader.load('./animation/3djs/gentilis_regular.typeface.json', function (font) {
        sysfont = font;
        console.log(11)
        initdata(stationID, 1)
        setInterval(function () {
            initdata(stationID, 0)
        }, 10000)

    })

    var loader1 = new THREE.TextureLoader();
    loader1.load("img/chk0.png", function (texture) {
        chk0material = new THREE.SpriteMaterial({map: texture, transparent: true})
    })
    loader1.load("img/chk1.png", function (texture) {
        chk1material = new THREE.SpriteMaterial({map: texture, transparent: true})
    })


}

//选枪点击事件——选择可充枪
function chkgun() {
    if (chksta == 0) {
        chksta = 1
        // $("#btn").text("取消选择枪")
        upgun()
    } else {
        chksta = 0
        // $("#btn").text("选择可充枪")
        downgun()
    }
}

//弹起准备充电的桩
function upgun() {
    var gunid, gungroup, checkedgun = 0;
    selectgun = [];
    readymaterial1.opacity = ordermaterial1.opacity = powermaterial1.opacity = endmaterial1.opacity = freematerial1.opacity = warnmaterial1.opacity = unlinkmaterial1.opacity = 1
    boxmaterial1.opacity = 0.7

    for (var i = 0; i < clickObjects.length; i++) {
        gungroup = clickObjects[i].parent;
        gunid = gungroup.name;

        var sta = 0;
        var gundata = gunlist[gunid]["gunNo"]
        var gungunnum = gundata.length;
        var arrtemp = [];
        for (var j = 0; j < gungunnum; j++) {
            if (gundata[j] == 0) {
                sta = 1;
                arrtemp.push(j)
                var sprite = new THREE.Sprite(chk1material)
                sprite.name = j;
                sprite.scale.set(15, 15, 15)
                sprite.position.x = 20
                sprite.position.y = 68
                if (gungunnum == 1) {

                } else if (gungunnum == 2) {
                    if (j == 0) {
                        sprite.position.z = -22
                    } else if (j == 1) {
                        sprite.position.z = 22
                    }
                } else if (gungunnum == 4) {
                    if (j == 0) {
                        sprite.position.z = -45
                    } else if (j == 1) {
                        sprite.position.z = -15
                    } else if (j == 2) {
                        sprite.position.z = 15
                    } else if (j == 3) {
                        sprite.position.z = 45
                    }
                }
                gungroup.add(sprite)
                checkedgun++;
                clickSprite.push(sprite)
            }
        }
        $("#checkedimg").text(checkedgun)
        if (arrtemp.length > 0) {
            selectgun.push({"index": i, "gunNo": arrtemp})
        }
        if (sta == 1) {//可充电桩
            new TWEEN.Tween(gungroup.position).to({y: 20}, 200).start();
            for (var j = 0; j < gungunnum; j++) {
                gungroup.children[j].material = getMaterial(gundata[j])
                gungroup.children[gungunnum + 3 + j].material = sysfontMaterial
            }
            gungroup.children[gungunnum].material = boxmaterial
            gungroup.children[gungunnum + 2].material = sysfontMaterial
        } else {
            new TWEEN.Tween(gungroup.position).to({y: 0}, 200).start();
            for (var j = 0; j < gungunnum; j++) {
                gungroup.children[j].material = getMaterial1(gundata[j])
                gungroup.children[gungunnum + 3 + j].material = sysfontMaterial1
            }
            gungroup.children[gungunnum].material = boxmaterial1
            gungroup.children[gungunnum + 2].material = sysfontMaterial1
        }
    }
    new TWEEN.Tween(readymaterial1).to({opacity: 0.3}, 300).start();
    new TWEEN.Tween(ordermaterial1).to({opacity: 0.3}, 300).start();
    new TWEEN.Tween(powermaterial1).to({opacity: 0.3}, 300).start();
    new TWEEN.Tween(endmaterial1).to({opacity: 0.3}, 300).start();
    new TWEEN.Tween(freematerial1).to({opacity: 0.3}, 300).start();
    new TWEEN.Tween(warnmaterial1).to({opacity: 0.3}, 300).start();
    new TWEEN.Tween(unlinkmaterial1).to({opacity: 0.3}, 300).start();
    new TWEEN.Tween(sysfontMaterial1).to({opacity: 0.3}, 300).start();
    new TWEEN.Tween(boxmaterial1).to({opacity: 0.2}, 300).start();

}

//还原
function downgun() {
    var gunid, gungroup;
    selectgun = [];
    clickSprite = [];
    for (var i = 0; i < clickObjects.length; i++) {
        gungroup = clickObjects[i].parent;
        gunid = gungroup.name;

        for (var j = 0; j < gungroup.children.length; j++) {
            if (gungroup.children[j].type == "Sprite") {
                var obj = gungroup.children[j];
                gungroup.remove(obj)
                scene.remove(obj)
                j--
            }
        }

        if (gungroup.position.y > 0) {
            new TWEEN.Tween(gungroup.position).to({y: 0}, 200).start();
        }
    }
    new TWEEN.Tween(readymaterial1).to({opacity: 1}, 300).start();
    new TWEEN.Tween(ordermaterial1).to({opacity: 1}, 300).start();
    new TWEEN.Tween(powermaterial1).to({opacity: 1}, 300).start();
    new TWEEN.Tween(endmaterial1).to({opacity: 1}, 300).start();
    new TWEEN.Tween(freematerial1).to({opacity: 1}, 300).start();
    new TWEEN.Tween(warnmaterial1).to({opacity: 1}, 300).start();
    new TWEEN.Tween(unlinkmaterial1).to({opacity: 1}, 300).start();
    new TWEEN.Tween(sysfontMaterial1).to({opacity: 1}, 300).start();
    new TWEEN.Tween(boxmaterial1).to({opacity: 0.7}, 300).start().onComplete(function () {
        setmater()
    });
}

//动画执行完毕还原材质
function setmater() {
    var gunid, gungroup;

    for (var i = 0; i < clickObjects.length; i++) {
        gungroup = clickObjects[i].parent;
        gunid = gungroup.name;

        var gundata = gunlist[gunid]["gunNo"]
        var gungunnum = gundata.length
        for (var j = 0; j < gungunnum; j++) {
            gungroup.children[j].material = getMaterial(gundata[j])
            gungroup.children[gungunnum + 3 + j].material = sysfontMaterial
        }
        gungroup.children[gungunnum].material = boxmaterial
        gungroup.children[gungunnum + 2].material = sysfontMaterial
    }
    readymaterial1.opacity = ordermaterial1.opacity = powermaterial1.opacity = endmaterial1.opacity = freematerial1.opacity = warnmaterial1.opacity = unlinkmaterial1.opacity = 1
    boxmaterial1.opacity = 0.7
}


//绑定数据
function bindobj() {

    if (chksta == 0) {  //正常模式绑定数据
        var gungroup, gunid
        for (var i = 0; i < clickObjects.length; i++) {
            gungroup = clickObjects[i].parent
            gunid = gungroup.name;
            var gunarr = gunlist[gunid]["gunNo"]
            var gunnum = gunarr.length;
            for (var j = 0; j < gungroup.children.length; j++) {   //移除sprite
                if (gungroup.children[j].type == "Sprite") {
                    var obj11 = gungroup.children[j];
                    gungroup.remove(obj11)
                    scene.remove(obj11)
                    j--
                }
            }
            for (var j = 0; j < gunnum; j++) {
                gungroup.children[j].material = getMaterial(gunarr[j])
                gungroup.children[gunnum + 3 + j].material = sysfontMaterial
            }
            gungroup.children[gunnum].material = boxmaterial
            gungroup.children[gunnum + 2].material = sysfontMaterial

            if (gungroup.position.y != 0) {
                new TWEEN.Tween(gungroup.position).to({y: 0}, 500).start();
            }
        }
    }
}

//根据数据添加几何体==============================================================================================================================
function addgun() {
    for (var i = 0; i < gunlist.length; i++) {
        if (gunlist[i].gunNo.length == 1) {
            addgun1(gunlist[i], i)
        } else if (gunlist[i].gunNo.length == 2) {
            addgun2(gunlist[i], i)
        } else if (gunlist[i].gunNo.length == 4) {
            addgun4(gunlist[i], i)
        }
    }
}

//添加单枪
function addgun1(data, t) {

    var gungroup = groupmodel1.clone();

    var boxborder = new THREE.BoxHelper(gungroup.children[1], 0x74d684);//设置边框
    gungroup.add(boxborder);

    var shapes = sysfont.generateShapes(data["pileNum"], 25);
    var fontGeometry = new THREE.ShapeGeometry(shapes);
    fontGeometry.computeBoundingBox();
    var font = new THREE.Mesh(fontGeometry, sysfontMaterial);
    font.position.y = 3.25
    font.position.x = -44
    font.position.z = -16
    font.rotation.z = -Math.PI / 2;
    font.rotation.x = -Math.PI / 2;
    gungroup.add(font);

    var shapeA = sysfont.generateShapes("A", 13);
    var fontGeometryA = new THREE.ShapeGeometry(shapeA);
    fontGeometryA.computeBoundingBox();
    var fontA = new THREE.Mesh(fontGeometryA, sysfontMaterial);
    fontA.position.y = 3.25
    fontA.position.x = 42
    fontA.position.z = -5
    fontA.rotation.z = -Math.PI / 2;
    fontA.rotation.x = -Math.PI / 2;
    gungroup.add(fontA);

    gungroup.position.z = data["piley"]
    gungroup.position.x = data["pilex"]
    gungroup.rotation.y = Math.PI * (data["pilea"] / 180);

    gungroup.children[0].material = getMaterial(data["gunNo"][0])
    gungroup.name = t
    scene.add(gungroup);
    clickObjects.push(gungroup.children[1])
}

//添加双枪
function addgun2(data, t) {
    var gungroup = groupmodel2.clone();

    var boxborder = new THREE.BoxHelper(gungroup.children[2], 0x74d684);//设置边框
    gungroup.add(boxborder);
    // 绑定文字标号
    var shapes = sysfont.generateShapes(data["pileNum"], 25);
    var fontGeometry = new THREE.ShapeGeometry(shapes);
    fontGeometry.computeBoundingBox();
    var font = new THREE.Mesh(fontGeometry, sysfontMaterial);
    font.position.y = 3.25
    font.position.x = -44
    font.position.z = -16
    font.rotation.z = -Math.PI / 2;
    font.rotation.x = -Math.PI / 2;
    gungroup.add(font);

    // 绑定文字标号
    var shapeA = sysfont.generateShapes("A", 13);
    var fontGeometryA = new THREE.ShapeGeometry(shapeA);
    fontGeometryA.computeBoundingBox();
    var fontA = new THREE.Mesh(fontGeometryA, sysfontMaterial);
    fontA.position.y = 3.25
    fontA.position.x = 42
    fontA.position.z = -26
    fontA.rotation.z = -Math.PI / 2;
    fontA.rotation.x = -Math.PI / 2;
    gungroup.add(fontA);

    // 绑定文字标号
    var shapeB = sysfont.generateShapes("B", 13);
    var fontGeometryB = new THREE.ShapeGeometry(shapeB);
    fontGeometryB.computeBoundingBox();
    var fontB = new THREE.Mesh(fontGeometryB, sysfontMaterial);
    fontB.position.y = 3.25
    fontB.position.x = 42
    fontB.position.z = 16
    fontB.rotation.z = -Math.PI / 2;
    fontB.rotation.x = -Math.PI / 2;
    gungroup.add(fontB);


    gungroup.position.z = data["piley"]
    gungroup.position.x = data["pilex"]
    gungroup.rotation.y = Math.PI * (data["pilea"] / 180);

    gungroup.children[0].material = getMaterial(data["gunNo"][0])
    gungroup.children[1].material = getMaterial(data["gunNo"][1]);
    gungroup.children[2].material = boxmaterial
    gungroup.name = t
    scene.add(gungroup);
    clickObjects.push(gungroup.children[2])
}

//添加四枪
function addgun4(data, t) {
    var gungroup = groupmodel4.clone();

    var boxborder = new THREE.BoxHelper(gungroup.children[4], 0x74d684);//设置边框
    gungroup.add(boxborder);
    // 绑定文字标号
    var shapes = sysfont.generateShapes(data["pileNum"], 25);
    var fontGeometry = new THREE.ShapeGeometry(shapes);
    fontGeometry.computeBoundingBox();
    var font = new THREE.Mesh(fontGeometry, sysfontMaterial);
    font.position.y = 3.25
    font.position.x = -44
    font.position.z = -16
    font.rotation.z = -Math.PI / 2;
    font.rotation.x = -Math.PI / 2;
    gungroup.add(font);

    // 绑定文字标号
    var shapeA = sysfont.generateShapes("A", 13);
    var fontGeometryA = new THREE.ShapeGeometry(shapeA);
    fontGeometryA.computeBoundingBox();
    var fontA = new THREE.Mesh(fontGeometryA, sysfontMaterial);
    fontA.position.y = 3.25
    fontA.position.x = 42
    fontA.position.z = -51
    fontA.rotation.z = -Math.PI / 2;
    fontA.rotation.x = -Math.PI / 2;
    gungroup.add(fontA);

    // 绑定文字标号
    var shapeB = sysfont.generateShapes("B", 13);
    var fontGeometryB = new THREE.ShapeGeometry(shapeB);
    fontGeometryB.computeBoundingBox();
    var fontB = new THREE.Mesh(fontGeometryB, sysfontMaterial);
    fontB.position.y = 3.25
    fontB.position.x = 42
    fontB.position.z = -21
    fontB.rotation.z = -Math.PI / 2;
    fontB.rotation.x = -Math.PI / 2;
    gungroup.add(fontB);

    // 绑定文字标号
    var shapeC = sysfont.generateShapes("C", 13);
    var fontGeometryC = new THREE.ShapeGeometry(shapeC);
    fontGeometryC.computeBoundingBox();
    var fontC = new THREE.Mesh(fontGeometryC, sysfontMaterial);
    fontC.position.y = 3.25
    fontC.position.x = 42
    fontC.position.z = 9
    fontC.rotation.z = -Math.PI / 2;
    fontC.rotation.x = -Math.PI / 2;
    gungroup.add(fontC);

    // 绑定文字标号
    var shapeD = sysfont.generateShapes("D", 13);
    var fontGeometryD = new THREE.ShapeGeometry(shapeD);
    fontGeometryD.computeBoundingBox();
    var fontD = new THREE.Mesh(fontGeometryD, sysfontMaterial);
    fontD.position.y = 3.25
    fontD.position.x = 42
    fontD.position.z = 39
    fontD.rotation.z = -Math.PI / 2;
    fontD.rotation.x = -Math.PI / 2;
    gungroup.add(fontD);


    gungroup.position.z = data["piley"]
    gungroup.position.x = data["pilex"]
    gungroup.rotation.y = Math.PI * (data["pilea"] / 180);


    gungroup.children[0].material = getMaterial(data["gunNo"][0]);
    gungroup.children[1].material = getMaterial(data["gunNo"][1]);
    gungroup.children[2].material = getMaterial(data["gunNo"][2]);
    gungroup.children[3].material = getMaterial(data["gunNo"][3]);
    gungroup.children[4].material = boxmaterial
    gungroup.name = t
    scene.add(gungroup);
    clickObjects.push(gungroup.children[4])
}

//获取不透明材质
function getMaterial(n) {
    switch (n) {
        case 0:
            return readymaterial;     //0准备
            break;
        case 1:
            return ordermaterial;    //1预约
            break;
        case 2:
            return powermaterial;    //2充电
            break;
        case 3:
            return endmaterial;      //3充满
            break;
        case 4:
            return freematerial;      //4空闲
            break;
        case 5:
            return warnmaterial;      //5告警
            break;
        case 6:
            return unlinkmaterial;    //6离线
    }
}

//获取材质1，过渡渐变使用
function getMaterial1(n) {
    switch (n) {
        case 0:
            return readymaterial1;     //0准备
            break;
        case 1:
            return ordermaterial1;    //1预约
            break;
        case 2:
            return powermaterial1;    //2充电
            break;
        case 3:
            return endmaterial1;      //3充满
            break;
        case 4:
            return freematerial1;      //4空闲
            break;
        case 5:
            return warnmaterial1;      //5告警
            break;
        case 6:
            return unlinkmaterial1;    //6离线
    }
}

//获取材质2，单个渐变使用
function getMaterial2(n) {
    switch (n) {
        case 0:
            return readymaterial2;     //0准备
            break;
        case 1:
            return ordermaterial2;    //1预约
            break;
        case 2:
            return powermaterial2;    //2充电
            break;
        case 3:
            return endmaterial2;      //3充满
            break;
        case 4:
            return freematerial2;      //4空闲
            break;
        case 5:
            return warnmaterial2;      //5告警
            break;
        case 6:
            return unlinkmaterial2;    //6离线
    }
}

//数据初始化修改
function initsize(a, b) {
    var arrlen = a.length,
        fwidth = Math.ceil(Math.sqrt(a.length)),
        fheight = fwidth;
    for (; fheight * fwidth >= arrlen; fheight--) {
    }
    ;
    fheight++;
    var floow = 128 * fwidth + 48 * (fwidth - 1) + 100,
        flooh = 128 * fheight + 48 * (fheight - 1) + 100,
        parr = [];
    mapsize["width"] = floow;
    mapsize["height"] = flooh;


    for (var i = 0; i < arrlen; i++) {
        var x = parseInt(i / fheight),
            y = i % fheight,
            wx = -floow / 2 + 114 + x * 176,
            wy = -flooh / 2 + 114 + y * 176,
            tmparr = [],
            tmparr1 = a[i]["gunJson"];
        for (var j = 0; j < tmparr1.length; j++) {
            tmparr[parseInt(tmparr1[j]["gunNo"])] = strTonum(tmparr1[j]["gunState"])
        }
        tmpjson = {
            "pileid": a[i]["pileID"],
            "pileNum": (i > 9 ? i.toString(): "0" + i),
            "pilex": wx,
            "piley": wy,
            "pilea": (x % 2 == 0 ? 180 : 0),
            "gunNo": tmparr
        }
        parr.push(tmpjson)
    }
    gunlist = parr;
    if (b == 1) {
        addgun()
        initObject()
        // initGrid();
    } else {
        console.log(gunlist)
        bindobj()
    }

}


//平台数据转状态
function strTonum(a) {
    switch (a) {
        case "CHARGEPREPARE":
            return 0;
            break;
        case "BESPEAK":
            return 1;
            break;
        case "CHARGING":
            return 2;
            break;
        case "CHARGEFINISH":
            return 3;
            break;
        case "FREE":
            return 4;
            break;
        case "Problem":
            return 5;
            break;
        case "OFFLINE":
            return 6;
            break;
        default :
            return 0;
    }
}
// initdata(219,1)
function initdata(stationID, num) {
    $.ajax({
        url: "/statistic/gJStationViewAjax/getGunData.json",
        type: "post",
        dataType: "json",
        data: {"stationID": stationID},
        success: function (res) {
            console.log(res)
            initsize(res, num)
        }
    })
}