$(document).ready(function() {
	var capArr = [];
	var itemArr = [];

	$.ajax({
		type : "GET",
		url : "/XML/Garbage.xml", // change to full path of file on server
		dataType : "xml",
		success : parseXml,
		complete : setupAC,
		failure : function(data) {
			alert("XML File could not be found");
		}
	});

	function parseXml(xml) {
		//find every query value
		$(xml).find("Obj").each(function() {
			capArr.push($(this).find("CAP").text());
			$(this).find("GarbageTypes").find("Garbage").each(function() {
				$(this).find("GarbageElements").find("Item").each(function() {
					itemArr.push($(this).text());
				});
			});
		});
	}

	function setupAC() {
		$("input#where").autocomplete({
			source : capArr,
			minLength : 1,
			select : function(event, ui) {
				$("input#where").val(ui.item.value);
			}
		});
		$("input#what").autocomplete({
			source : itemArr,
			minLength : 1,
			select : function(event, ui) {
				$("input#what").val(ui.item.value);
			}
		});
	}

});
