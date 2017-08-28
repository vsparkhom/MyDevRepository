var myTab = document.getElementById("myTable");

var leftTab = document.getElementById("left-func");
var rightTab = document.getElementById("right-func");
var topTab = document.getElementById("top-func");
var botTab = document.getElementById("bot-func");


addCol.onclick = addCol;
function addCol() {
	for (i = 0; i < myTab.rows.length; i++){
	myTab.rows[i].insertCell(-1);
	}
	topTab.rows[0].insertCell(-1);
	botTab.rows[0].insertCell(-1);
}

addRow.onclick = addRow;
function addRow() {
	var row = document.createElement("tr");
	myTab.appendChild(row);
	for (i = 0; i < myTab.rows[0].cells.length; i++){
	row.insertCell(i);
	}
	leftTab.insertRow(-1).insertCell(0);
	rightTab.insertRow(-1).insertCell(0);
}

var r; // = myRow;
var c; // = myColumn;

	console.log("c    myColumn is: " + c);
	console.log("c    myRow is: " + r);

delCol.onclick = delCol;
function delCol() {
	console.log("c    myColumn is: " + c);
	console.log("c    myRow is: " + r);
	
	if (myTab.rows[0].cells.length >1) {
  	for (i = 0; i < myTab.rows.length; i++){
		myTab.rows[i].deleteCell(c);
		}
	topTab.rows[0].deleteCell(-1);
	botTab.rows[0].deleteCell(-1);
	}
	else{
  	alert ("Can't do this, you have only one column!");
	}
}


delRow.onclick = delRow;
function delRow() {
	console.log("r    myColumn is: " + c);
	console.log("r    myRow is: " + r);
	
	if (myTable.rows.length >1) {
		myTab.deleteRow(r);
		leftTab.deleteRow(-1);
		rightTab.deleteRow(-1);
		}
	else {
		alert ("Can't do this, you have only one row!");
	}
}


function hideBut(){
	for (i = 0; i < topTab.rows[0].cells.length; i++){
		topTab.rows[0].cells[i].innerHTML = "";
		topTab.rows[0].cells[i].removeAttribute("class");
		topTab.rows[0].cells[i].removeAttribute("id");
		topTab.rows[0].cells[i].removeAttribute("onclick");
	}
		for (i=0; i < leftTab.rows.length; i++){
		leftTab.rows[i].cells[0].innerHTML = "";
		leftTab.rows[i].cells[0].removeAttribute("class");
		leftTab.rows[i].cells[0].removeAttribute("id");
		leftTab.rows[i].cells[0].removeAttribute("onclick");
	}
}

if (bigTable.onmouseout == false) {
	hideBut;
}

myTable.onmouseover = showBut;
function showBut(event) {
	with(event.target || event.srcElement) {
		var myRow = parentNode.rowIndex;
		var myColumn = cellIndex;
			
		//console.log("myColumn is: " + myColumn);
		//console.log("myRow is: " + myRow);
		//console.log("-----------------------");
	}
	
	r = myRow;
	c = myColumn;
	
	for (i = 0; i < topTab.rows[0].cells.length; i++){
		topTab.rows[0].cells[i].innerHTML = "";
		topTab.rows[0].cells[i].removeAttribute("class");
		topTab.rows[0].cells[i].removeAttribute("id");
		topTab.rows[0].cells[i].removeAttribute("onclick");
	}
	for (i=0; i < leftTab.rows.length; i++){
		leftTab.rows[i].cells[0].innerHTML = "";
		leftTab.rows[i].cells[0].removeAttribute("class");
		leftTab.rows[i].cells[0].removeAttribute("id");
		leftTab.rows[i].cells[0].removeAttribute("onclick");
	}
	
	topTab.rows[0].cells[myColumn].className = "del";
	topTab.rows[0].cells[myColumn].id = "delCol";
	topTab.rows[0].cells[myColumn].setAttribute("onclick","delCol()");
		
	leftTab.rows[myRow].cells[0].className = "del";
	leftTab.rows[myRow].cells[0].id = "delRow";
	leftTab.rows[myRow].cells[0].setAttribute("onclick","delRow()");
	
	rightTab.rows[0].cells[0].className = "add";
	rightTab.rows[0].cells[0].id = "addCol";
	rightTab.rows[0].cells[0].setAttribute("onclick","addCol()");
	
	botTab.rows[0].cells[0].className = "add";
	botTab.rows[0].cells[0].id = "addRow";
	botTab.rows[0].cells[0].setAttribute("onclick","addRow()");
	
	
	//return (r, c);
}