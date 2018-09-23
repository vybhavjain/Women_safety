function doGet(e){

 // Change Spread Sheet url
  
 var ss = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/1o6R9ULbtZOS2Ep5bFJFs0HT46hNyq405ydHQ7VI_Qgw/edit#gid=0");
// Sheet Name, Chnage Sheet1 to Users in Spread Sheet. Or any other name as you wish
 var sheet = ss.getSheetByName("location");
  
 return getUsers(sheet); 
  
}

function getUsers(sheet){
  var jo = {};
  var dataArray = [];

  var rows = sheet.getRange(2,1,sheet.getLastRow()-1, sheet.getLastColumn()).getValues();
  
  for(var i = 0, l= rows.length; i<l ; i++){
    var dataRow = rows[i];
    var record = {};
    record['latitude'] = dataRow[0];
    record['longitude'] = dataRow[1];
    
    
    dataArray.push(record);
    
  }  
  
  jo.user = dataArray;
  
  var result = JSON.stringify(jo);
  
  return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.JSON);
  
}  
