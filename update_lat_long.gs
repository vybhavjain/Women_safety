function doGet(e){
  return handleResponse(e);
  
}

//Recieve parameter and pass it to function to handle

function doPost(e){
  return handleResponse(e);
}  

// here handle with parameter

function handleResponse(request) {
  var output  = ContentService.createTextOutput();
  
  //create varibles to recieve respective parameters
  var index = request.parameter.index;
  var latitude = request.parameter.latitude;
  var longitude = request.parameter.longitude;
  
  
  //open your Spread sheet by passing id
  
  var ss= SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1viZt-fcerDrIyNjzkUXcDa9Tgv83PklKPtydFCZiFP4/edit#gid=0");
  var sheet=ss.getSheetByName("login");
    
  var rowData = sheet.getRange(index,8).setValue(latitude);  
  var rowData = sheet.getRange(index,9).setValue(longitude); 

  
  var callback = request.parameters.callback;
  if (callback === undefined) {
    output.setContent(JSON.stringify("Success"));
  } else {
    output.setContent(callback + "(" + JSON.stringify("Success") + ")");
  }

  output.setMimeType(ContentService.MimeType.JSON);
  
  return output;


}
