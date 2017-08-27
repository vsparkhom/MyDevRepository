var IP_REGEXP = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.5\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/g;
var TEXT_REGEXP = /^12-[0-9]{4}-[a-zа-я]{2}$/g;

function calcV() {
    var ipValue = document.getElementById('ip').value;
    var textValue = document.getElementById('text').value;

    //validate IP field
    if (validateInput(ipValue, IP_REGEXP)) {
        console.log("IP Correct: " + ipValue);
        document.getElementById('ipValidationIndicator').innerHTML = "<font color='green'>Correct</font>";
    } else {
        console.log("IP Wrong: " + ipValue);
        document.getElementById('ipValidationIndicator').innerHTML = "<font color='red'>Wrong</font>";
    }

    //validate TEXT field
    if (validateInput(textValue, TEXT_REGEXP)) {
        console.log("Text Correct: " + textValue);
        document.getElementById('textValidationIndicator').innerHTML = "<font color='green'>Correct</font>";
    } else {
        console.log("Text Wrong: " + textValue);
        document.getElementById('textValidationIndicator').innerHTML = "<font color='red'>Wrong</font>";
    }

}

function validateInput(str, reg) {
    var res = str.search(reg);
    console.log("res: " + res);
    return res >= 0;
}

function clearFields() {
    document.getElementById('ip').value = "";
    document.getElementById('text').value = "";
    document.getElementById('ipValidationIndicator').innerHTML = null;
    document.getElementById('textValidationIndicator').innerHTML = null;
}

