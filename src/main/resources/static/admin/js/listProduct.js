let products = {};

products.initProductTable = function () {
    $('#product-dataTables').DataTable({
        ajax: {
            url: `http://localhost:8053/api/products`,
            method: "GET",
            dataType: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: 'nameProduct',name: 'nameProduct', title: 'Tên sản phẩm', orderable: true,
                'render': function (data, type, row) {
                    return data;
                }
            },
            {
                data: 'price',name: 'price', title: 'Giá',
                'render': function (data, type, row) {
                    return data;
                }
            },
            {
                data: 'productType.nameProductType',name: 'productType.nameProductType', title: 'Loại sản phẩm',
                'render': function (data, type, row) {
                    return data;
                }
            },
            {
                data: "id", name: "action", title: "Chức năng", orderable: false,
                "render": function (data, type, row, meta) {
                    return "<a class='mr-2' href='javascript:;' title='Chỉnh sửa' onclick='products.get(" + data + ")'><i class='fa fa-edit'></i></a> " +
                        "<a class='mr-2' href='javascript:;' title='Xóa' onclick='products.delete(" + data + ")' ><i class='fa fa-trash'></i></a>" +
                        "<a class='mr-2' href='javascript:;' title='Thông tin chi tiết' onclick='products.viewProduct(" + data + ")' ><i class='fas fa-eye'></i></a>"
                }
            },

        ],
        autofill: true,
        select: true,
        responsive: true,
        buttons: true,
        length: 10,
    });
}


products.init = function () {
    console.log("vao init");
    products.initProductTable();
};
$(document).ready( function () {
    products.init();
} );