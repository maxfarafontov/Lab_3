function validate_name (input, form){
    valid = true;
    if ( input.value == "" )
    {
        alert( "pls, fill text input" );
        window.document
        document.ElementById('save').disabled = true;
        input.value = "111111111111111111";
        return;
    }
    document.getElementById('save').disabled = false;
    return valid;
}
