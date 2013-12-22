$(document).ready(function() {
			//Function that writes the table
			var table =document.getElementById("tab");
			var counter = 1;
			var s = "<table>";
			var coin = "<img src=\"images/coin.png\">";
			for (var i = 0; i < objs.length; i=i+3) {
				//for each line, the name and the CAP of a city are written
				s+="<tr>";
				s+="<td>"+coin+") "+objs[i][1]+", "+objs[i][0]+"</td>";
				try{
					s+="<td>"+coin+") "+objs[i+1][1]+", "+objs[i+1][0]+"</td>";
				}catch(err){
					
				}
				try{
					s+="<td>"+coin+") "+objs[i+2][1]+", "+objs[i+2][0]+"</td>";
				}catch(err){
					
				}
				s+="</tr>";
				counter += 3;
			}
			s+="</table>";
			//and we include them in the html
			table.innerHTML=s;
				});