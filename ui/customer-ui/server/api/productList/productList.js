const json = require('jsonfile');
let rest = require('restler');
let configuration = require('../../config/backend-config');

exports.getProductList = function (request, response) {
  const file = '../customer-ui/server/api/mock/jsons/productList.json';

  //from file
  json.readFile(file, function (err, obj) {
    if (err) {
      err.status(500);
      console.error(err);
    }
    response.status(200).send(obj);
  });

  //for spring
  let body = request.body;

//   rest.postJson(configuration.perEnvironment.backendUrl + '/products', body)
//     .on('success', (backendResponseBody, backendResponseMeta) => {
//     response.json(backendResponseBody);
// })
// .on('fail', (backendResponseBody, backendResponseMeta) => {
//     response.status(backendResponseMeta.statusCode).send(backendResponseBody);
// });
};
