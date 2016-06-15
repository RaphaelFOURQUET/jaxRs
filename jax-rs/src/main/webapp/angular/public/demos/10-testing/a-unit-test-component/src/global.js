function sortBySize(array){
    
    return array.sort(function(name1, name2){
        return name1.length < name2.length ? -1 :1;  
    });
    
}
