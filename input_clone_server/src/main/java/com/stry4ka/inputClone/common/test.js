var arr = [{name: "Test1"}, {name: "Test21222"}, {name: "Test333333333"}];

var filteredItems =_.filter(arr, function (item) {
    return item.name.length > 5;
});

var result = _.map(filteredItems, function (item) {
    return item.name;
});

console.log(result);