function pageSearch(href,pageNum){
    let tempForm = document.createElement("form");
    tempForm.id = "tempForm1";
    tempForm.method = "post";
    tempForm.action = href;

    let title=$('#title').val();
    let hideInput1 = document.createElement("input");
    hideInput1.type = "hidden";
    hideInput1.name = "title";
    hideInput1.value = title;
    tempForm.appendChild(hideInput1);

    let hideInput2 = document.createElement("input");
    hideInput2.type = "hidden";
    hideInput2.name = "pageNum";
    hideInput2.value = pageNum;
    tempForm.appendChild(hideInput2);

    if (document.all) {    // 兼容不同浏览器
        tempForm.attachEvent("onsubmit", function () {
        });        //IE
    } else {
        tempForm.addEventListener("submit", function () {
        }, false);    //firefox
    }
    document.body.appendChild(tempForm);
    if (document.all) {    // 兼容不同浏览器
        tempForm.fireEvent("onsubmit");
    } else {
        tempForm.dispatchEvent(new Event("submit"));
    }
    tempForm.submit();
    // loading.hide();
    document.body.removeChild(tempForm);
}

function downloadFile(filepath){
    //要记得注释掉 要用数据库里的文件路径
    // filepath="D:/Project/IDEA/ConferenceSystem/target/classes/static/file/论文1.doc";
    let tempForm = document.createElement("form");
    tempForm.id = "tempForm1";
    tempForm.method = "post";
    tempForm.action = "/download";

    let hideInput1 = document.createElement("input");
    hideInput1.type = "hidden";
    hideInput1.name = "filepath";
    hideInput1.value = filepath;
    tempForm.appendChild(hideInput1);

    if (document.all) {    // 兼容不同浏览器
        tempForm.attachEvent("onsubmit", function () {
        });        //IE
    } else {
        tempForm.addEventListener("submit", function () {
        }, false);    //firefox
    }
    document.body.appendChild(tempForm);
    if (document.all) {    // 兼容不同浏览器
        tempForm.fireEvent("onsubmit");
    } else {
        tempForm.dispatchEvent(new Event("submit"));
    }
    tempForm.submit();
    // loading.hide();
    document.body.removeChild(tempForm);
}

function showConference(conferId){
    let tempForm = document.createElement("form");
    tempForm.id = "tempForm1";
    tempForm.method = "post";
    tempForm.action = "/showConference";

    let hideInput1 = document.createElement("input");
    hideInput1.type = "hidden";
    hideInput1.name = "conferId";
    hideInput1.value = conferId;
    tempForm.appendChild(hideInput1);

    if (document.all) {    // 兼容不同浏览器
        tempForm.attachEvent("onsubmit", function () {
        });        //IE
    } else {
        tempForm.addEventListener("submit", function () {
        }, false);    //firefox
    }
    document.body.appendChild(tempForm);
    if (document.all) {    // 兼容不同浏览器
        tempForm.fireEvent("onsubmit");
    } else {
        tempForm.dispatchEvent(new Event("submit"));
    }
    tempForm.submit();
    // loading.hide();
    document.body.removeChild(tempForm);
}

function showPaper(conferId){
    let tempForm = document.createElement("form");
    tempForm.id = "tempForm1";
    tempForm.method = "post";
    tempForm.action = "/showPaper";

    let hideInput1 = document.createElement("input");
    hideInput1.type = "hidden";
    hideInput1.name = "conferId";
    hideInput1.value = conferId;
    tempForm.appendChild(hideInput1);

    if (document.all) {    // 兼容不同浏览器
        tempForm.attachEvent("onsubmit", function () {
        });        //IE
    } else {
        tempForm.addEventListener("submit", function () {
        }, false);    //firefox
    }
    document.body.appendChild(tempForm);
    if (document.all) {    // 兼容不同浏览器
        tempForm.fireEvent("onsubmit");
    } else {
        tempForm.dispatchEvent(new Event("submit"));
    }
    tempForm.submit();
    // loading.hide();
    document.body.removeChild(tempForm);
}

function pageSearchPaper(href,pageNum){
    let tempForm = document.createElement("form");
    tempForm.id = "tempForm1";
    tempForm.method = "post";
    tempForm.action = href;

    let conferId=$('#conferId').val();
    let hideInput1 = document.createElement("input");
    hideInput1.type = "hidden";
    hideInput1.name = "conferId";
    hideInput1.value = conferId;
    tempForm.appendChild(hideInput1);

    let hideInput2 = document.createElement("input");
    hideInput2.type = "hidden";
    hideInput2.name = "pageNum";
    hideInput2.value = pageNum;
    tempForm.appendChild(hideInput2);

    if (document.all) {    // 兼容不同浏览器
        tempForm.attachEvent("onsubmit", function () {
        });        //IE
    } else {
        tempForm.addEventListener("submit", function () {
        }, false);    //firefox
    }
    document.body.appendChild(tempForm);
    if (document.all) {    // 兼容不同浏览器
        tempForm.fireEvent("onsubmit");
    } else {
        tempForm.dispatchEvent(new Event("submit"));
    }
    tempForm.submit();
    // loading.hide();
    document.body.removeChild(tempForm);
}