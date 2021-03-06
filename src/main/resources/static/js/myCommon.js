$.fn.serializeObject = function() {
    var serializeObj = {};
    var array = this.serializeArray();
    var str = this.serialize();
    $(array).each(function() {
        if(serializeObj[this.name]) {
            if($.isArray(serializeObj[this.name])) {
                serializeObj[this.name].push(this.value);
            }else{
                serializeObj[this.name] = [serializeObj[this.name], this.value];
            }
        }else{
            serializeObj[this.name] = this.value;
        }
    });
    return serializeObj;
};


$.fn.serializeObject = function() {
    var serializeObj = {};
    var array = this.serializeArray();
    var str = this.serialize();
    $(array).each(function() {
        if(serializeObj[this.name]) {
            if($.isArray(serializeObj[this.name])) {
                serializeObj[this.name].push(this.value);
            }else{
                serializeObj[this.name] = [serializeObj[this.name], this.value];
            }
        }else{
            serializeObj[this.name] = this.value;
        }
    });
    return serializeObj;
};


function getUrlParams(name) { // 不传name返回所有值，否则返回对应值
    var url = window.location.search;
    if (url.indexOf('?') == 1) { return false; }
    url = url.substr(1);
    url = url.split('&');
    var name = name || '';
    var nameres;
    // 获取全部参数及其值
    for(var i=0;i<url.length;i++) {
        var info = url[i].split('=');
        var obj = {};
        obj[info[0]] = decodeURI(info[1]);
        url[i] = obj;
    }
    // 如果传入一个参数名称，就匹配其值
    if (name) {
        for(var i=0;i<url.length;i++) {
            for (var key in url[i]) {
                if (key == name) {
                    nameres = url[i][key];
                }
            }
        }
    } else {
        nameres = url;
    }
    // 返回结果
    return nameres;
}