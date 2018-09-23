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
  
  var name = request.parameter.name;
  var phonenumber = request.parameter.phonenumber;
  var email = request.parameter.email; 
  var emergencyname1 = request.parameter.emergencyname1;
  var emergencyphonenumber1 = request.parameter.emergencyphonenumber1;
  var emergencyname2 = request.parameter.emergencyname2;
  var emergencyphonenumber2 = request.parameter.emergencyphonenumber2;
  
  //open your Spread sheet by passing id

  
  var ss= SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1viZt-fcerDrIyNjzkUXcDa9Tgv83PklKPtydFCZiFP4/edit#gid=0");
  var sheet=ss.getSheetByName("login");
  
  //add new row with recieved parameter from client
  
  var rowData = sheet.appendRow([name,phonenumber,email,address,emergencyname1,emergencyphonenumber2,emergencyname2,emergencyphonenumber2]);  // added reference  
  
  
  var callback = request.parameters.callback;
  if (callback === undefined) {
    output.setContent(JSON.stringify("Success"));
  } else {
    output.setContent(callback + "(" + JSON.stringify("Success") + ")");
  }

  output.setMimeType(ContentService.MimeType.JSON);
  
  return output;


}
