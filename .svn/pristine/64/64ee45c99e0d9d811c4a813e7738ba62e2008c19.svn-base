//PER IMPLEMENTARE IL HELP SEARCH
//document.getElementById("searchTxt").onkeyup=function(){
//	alert(document.getElementById("searchTxt").value);};

function viewResult(s, id) {
	var queryDiv = document.getElementById(id);
	queryDiv.innerHTML = s;
}


document.getElementById("button").onclick = function() {
	var where = document.getElementById("where").value;
	var what = document.getElementById("what").value;
	what = (what.toLowerCase()).replace(/[^a-z]/g, "");
	var queryDiv = document.getElementById("query");
	var binDiv = document.getElementById("answer");

	for (var x = 0; x < objs.length; x++) {
		if (objs[x][0] == where) {
			for (var y = 0; y < objs[x][2].length; y++) {
				for (var z = 0; z < objs[x][2][y][2].length; z++) {
					if (objs[x][2][y][2][z] == what) {
						queryDiv.innerHTML = what + " a " + objs[x][1];
						binDiv.innerHTML = "L'oggetto " + what + " di tipo " + objs[x][2][y][0] + ", a " + objs[x][1] + " si butta nel cassonetto di colore " + objs[x][2][y][1];
						
						$.ajax({
							url : '/images/bin/' + objs[x][2][y][1] + '.png',
							type : 'HEAD',
							error : function() {
								var img = "<img src=\"images/bin/gray.png\">";
								binDiv.innerHTML += "<br>"+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img;
								for(var i; i<10 ; i++){
									binDiv.innerHTML += img;
								}
							},
							success : function() {
								var img = "<img src=\"images/bin/" + objs[x][2][y][1] + ".png\">";
								binDiv.innerHTML += "<br>"+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img+img;
							}
						});
						return;
					}
				}
			}
			queryDiv.innerHTML = "<div class=\"center\">Attenzione!</div>";
			binDiv.innerHTML = "<div class=\"center\">Non &egrave; stato possibile trovare " + what + " a " + objs[x][1] + "<br><img src=\"images/404.gif\" style=\"width:300px;\"></div>";
			return;
		}
	}
	queryDiv.innerHTML = "<div class=\"center\">Attenzione!</div>";
	binDiv.innerHTML = "<div class=\"center\">Non &egrave; stato possibile trovare una citt&agrave; corrispondente al CAP " + where+ "<br><img src=\"images/404.gif\" style=\"width:300px;\"></div>";
};
