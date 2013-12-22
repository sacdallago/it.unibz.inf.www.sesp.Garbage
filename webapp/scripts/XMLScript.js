//Inizio funzione per ottenere l'oggetto XML
function loadXMLDoc(dname)
{
if (window.XMLHttpRequest){xhttp=new XMLHttpRequest();}
else{xhttp=new ActiveXObject("Microsoft.XMLHTTP");}
xhttp.open("GET",dname,false);
xhttp.send();
return xhttp.responseXML;
} 
//Fine funzione

//Chiamata alla funzione per ottenere l'oggetto xml
xmlDoc=loadXMLDoc("XML/Garbage.xml");

//in x mettiamo tutti gli elementi astratti di tipo Obj
x=xmlDoc.getElementsByTagName("Obj");

//crea un array dove mettere gli oggetti
var objs = new Array(x.length);
for (var i = 0; i < x.length; i++) 
{//Per ogni oggetto Obj
	var obj = new Array(3);//crea un array dove mettere le sue info
	//Inserisci Cap  ------ 1 
	obj[0]=x[i].getElementsByTagName("CAP")[0].childNodes[0].nodeValue;
	//Inserisci CityName  ------ 2
	obj[1]=x[i].getElementsByTagName("CityName")[0].childNodes[0].nodeValue;
	
	//Retrieve delle informazioni dei diversi Garbage che andrano successivamente in una var "garbages"
	numberOfGarbageTypes=x[i].getElementsByTagName("GarbageTypes")[0].getElementsByTagName("Garbage").length;
	var garbages = new Array(numberOfGarbageTypes);
	for (var j = 0; j<numberOfGarbageTypes;j++)
	{//Per ogni tipo diverso di Garbage
		
		var garbageType= new Array(3);//crea un array dove mettere le sue info
		//Inserisci Tipo  ------ 3.1 
		garbageType[0]=x[i].getElementsByTagName("GarbageTypes")[0].getElementsByTagName("Garbage")[j].getElementsByTagName("Type")[0].childNodes[0].nodeValue;
		//Inserisci Colore  ------ 3.2
		garbageType[1]=x[i].getElementsByTagName("GarbageTypes")[0].getElementsByTagName("Garbage")[j].getElementsByTagName("BinColour")[0].childNodes[0].nodeValue;
		//Retrieve dei diversi elementi che andranno successivamente in una var "items"
		numberOfItems=x[i].getElementsByTagName("GarbageTypes")[0].getElementsByTagName("Garbage")[j].getElementsByTagName("Item").length;
		var items = new Array(numberOfItems);
		for (var k = 0; k<numberOfItems;k++)
		{//Per ogni Item, inseriscilo nella variabile "items"
			items[k]=x[i].getElementsByTagName("GarbageTypes")[0].getElementsByTagName("Garbage")[j].getElementsByTagName("Item")[k].childNodes[0].nodeValue;
		}
		//Inserisci array di Items  ------ 3.3 
		garbageType[2]=items;
		garbages[j]=garbageType;
	}
	obj[2]=garbages;
	objs[i]=obj;
}
/*
	Legenda:
		objs = Array contenente gli oggetti
		objs[n] = l'n-esimo oggetto.
		objs[n][m] = l'm-esima propriet� dell'oggetto (Cap, CityName, Garbage[])
		objs[n][m][o] = l'o-esima propriet� di Garbage (Type, BinColour, Item[])
		objs[n][m][o][p] = il p-esimo oggetto (item_1, item_2, ..., item_p)
*/