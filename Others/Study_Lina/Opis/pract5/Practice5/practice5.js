function calcV() {
    var r = document.getElementById('r').value;
    var h = document.getElementById('h').value;

    if (validateInputs(r, h)) {
        var S =  (2 * Math.PI * r * (h + r)).toFixed(3);
        document.getElementById('S').value = "Площадь поверхности цилиндра, S: " + S;
    } else {
        alert("Входящие параметры некорректны!");
    }


}

function validateInputs(r, h) {
    var reg = /^\d*\.?\d*$/;
    if (r==null || r=="" || h==null || h=="" || r.search(reg) < 0 || h.search(reg) < 0) {
        return false;
    } else {
        return true;
    }
}

function clearFields() {
    document.getElementById('r').value = "";
    document.getElementById('h').value = "";
    document.getElementById('S').value = "";
}

