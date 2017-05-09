function calcV() {
    var s = document.getElementById('s').value;
    var h = document.getElementById('h').value;

    if (validateInputs(s, h)) {
        var V =  s*h/3;
        document.getElementById('resultBlock').innerHTML = "Объем пирамиды, V: " + V;
    } else {
        alert("Входящие параметры некорректны!");
    }


}

function validateInputs(s, h) {
    if (s==null || s=="" || h==null || h=="" || isNaN(parseFloat(s)) || isNaN(parseFloat(h)) || s < 0 || h < 0) {
        return false;
    } else {
        return true;
    }
}

function clearFields() {
    document.getElementById('s').value = "";
    document.getElementById('h').value = "";
    document.getElementById('resultBlock').innerHTML = "";
}

