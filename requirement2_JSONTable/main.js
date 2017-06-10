
function TableFromJSON(){
	//AJAX(via jQuery) to request JSON data
	var jsonData;
	$.getJSON("http://127.0.0.1:8000/academy_award_actresses.json", function(data){
        jsonData = data;
		BuildTable(jsonData);
	});
}	


function BuildTable(jsonData){	
	//Get the header values
	var headerArr = [];
	for(var key in jsonData[0]){
		headerArr.push(key);
	}
	
	//Create table and header row
	var table = document.createElement("table");
	table.className = "table table-bordered table-striped table-hover"
	var tableRow = table.insertRow(-1);
	
	for(var i = 0; i < headerArr.length; i++){
		var tableHeader = document.createElement("th");
		tableHeader.innerHTML = headerArr[i];
		tableRow.appendChild(tableHeader);
	}
	
	//Add data and rows
	for(var i = 0; i < jsonData.length; i++) {
		tableRow = table.insertRow(-1);
		for(var j = 0; j < headerArr.length; j++) {
			var cell = tableRow.insertCell(-1);
			cell.innerHTML = jsonData[i][headerArr[j]];
		}
		
		
	}
	
	//Add table to div container
	var tableContainer = document.getElementById("jsonTable");
	tableContainer.appendChild(table);

}


$(document).ready(function(){
	TableFromJSON();
	//jQuery for click functionality
    $(document).on("click", "tr", function(){
		$(".modal-body").html($(this).text());
		$("#focusModal").modal('show');
        //alert($(this).text());
    });
});