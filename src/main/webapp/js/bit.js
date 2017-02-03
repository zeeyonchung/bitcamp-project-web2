var bit = function(selector) {
	
	if (typeof selector == 'function') {
		window.addEventListener('load', selector);
		return;
	}
	
	
	
	if (selector instanceof HTMLElement) {
		var tags = [selector];
	} else if (selector.indexOf('<') > -1) {
		var tags = [document.createElement(selector.substr(1, selector.length - 2))];
	} else {
		var tags = document.querySelectorAll(selector);
	}

	
	tags.append = function(childs) {
		for (var i = 0; i < this.length; i++) {
			for (var j = 0; j <childs.length; j++) {
				this[i].appendChild(childs[j]);
			}
		}
		return this;
	};
	
	
	
	tags.html = function(content) {
		if (content == undefined) {
			return this[0].innerHTML;
		}
		
		for (var i = 0; i < this.length; i++) {
			this[i].innerHTML = content;
		}
		return this;
	}
	
	
	
	tags.text = function(content) {
		if (content == undefined) {
			var value = "";
			for (var i = 0; i < this.length; i++) {
				value += this[i].textContent;
			}
			return value;
		}
		
		for (var i = 0; i < this.length; i++) {
			this[i].textContent = content;
		}
		return this;
	}
	
	
	
	tags.appendTo = function(parents) {
		for (var i = 0; i < parents.length; i++) {
			for (var j = 0; j < this.length; j++) {
				parents[i].appendChild(this[j]);
			}
		}
		return this;
	}
	
	
	
	tags.click = function(cb) {
		for (var i = 0; i < this.length; i++) {
			this[i].addEventListener('click', cb);
		}
		return this;
	}

	
	
	tags.css = function(propName, value) {
		for (var i = 0; i < this.length; i++) {
			this[i].style[propName] = value;
		}
		return this;
	}
	
	
	
	tags.attr = function(propName, value) {
		if (value == undefined) {
			return this[0].getAttribute(propName);
		}
		
		
		for (var i = 0; i < this.length; i++) {
			this[i].setAttribute(propName, value);
		}
		return this;
	}
	
	
	
	tags.val = function(value) {
		if (value == undefined) {
			return this[0].value;
		}
		
		for (var i = 0; i < this.length; i++) {
			if ("value" in this[i]) {
				this[i].value = value;
			}
		}
		return this;
	}
	
	
	tags.is = function(pseudoSelector) {
		var name = pseudoSelector.substr(1);
		return this[0][name];
	}
	
	
	
	return tags;
};











bit.get = function(url, success) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState < 4) {
			return;
		}
		success(xhr.responseText);

	}
	xhr.open('get', url, true);
	xhr.send();
};



bit.getJSON = function(url, success) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState < 4) {
			return;
		}
		success(JSON.parse(xhr.responseText));

	}
	xhr.open('get', url, true);
	xhr.send();
};



bit.post = function(url, data, success, dataType) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState < 4) {
			return;
		}
	

		if (dataType == undefined || dataType == 'text') {
			success((xhr.responseText));
		} else if (dataType == 'json') {
			success(JSON.parse(xhr.responseText));
		}

	}
	xhr.open('post', url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
	xhr.send(toQueryString(data));
}






function toQueryString(obj) {
	var qs = "";
	for (var propName in obj) {
		if (qs.length > 0) {
			qs += "&";
		}
		qs += propName + "=" + obj[propName];
	}
	return qs;
}


function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}



function setCookie(cname, cvalue, exdays, path) {
	if (path == undefined) {
		path = "/";
	}
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=" + path;
}



var $ = bit;