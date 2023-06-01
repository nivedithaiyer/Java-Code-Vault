$(document).ready(function() {
    // Fetch orders
    alert("test");
    $.ajax({
        url: "/NivKart/api/orders",
        method: "GET",
        success: function(data) {
			alert("api orders hit");
            var orderList = $("#orderList");
            $.each(data, function(index, order) {
				alert("updating list item");
                var listItem = "<li>" + order.orderId + ": " + order.productName + "</li>";
                orderList.append(listItem);
            });
        }
    });

    // Fetch inventory
    $.ajax({
        url: "/NivKart/api/inventory",
        method: "GET",
        success: function(data) {
            var inventoryList = $("#inventoryList");
            $.each(data, function(index, item) {
                var listItem = "<li>" + item.productName + ": " + item.quantity + " in stock</li>";
                inventoryList.append(listItem);
            });
        }
    });

   /* // Fetch notifications
    $.ajax({
        url: "/NivKart/api/notifications",
        method: "GET",
        success: function(data) {
            var notificationList = $("#notificationList");
            $.each(data, function(index, notification) {
                var listItem = "<li>" + notification.message + "</li>";
                notificationList.append(listItem);
            });
        }
    });*/
});
