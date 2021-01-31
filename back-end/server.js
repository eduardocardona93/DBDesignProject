'use strict';
const dbOperations = require('./scripts/dbOperations');
var express = require('express');
var requestsHandler = require('./scripts/requestsHandlers');
var bodyParser = require('body-parser');
var multer = require('multer');
var upload = multer();
var app = express();
var port = process.env.PORT || 3000;
// for parsing application/json
app.use(bodyParser.json());

// for parsing application/xwww-
app.use(bodyParser.urlencoded({ extended: true }));
//form-urlencoded

// for parsing multipart/form-data
app.use(upload.array());
app.use(express.static('public'));


app.use(express.json());

var api = "/api/v1";

app.get(api + '/test', (req, res) => {
    // requests.testRequest(req, res);
    dbOperations.getAllPruebas().then(result => {
        res.send(result);
    })
})
app.get(api + '/test/:custId', (req, res) => {

    var custId = req.params.custId;
    dbOperations.getOnePrueba(custId).then(result => {
        res.json(result);
    })
})

app.post(api + '/test', (req, res) => {

    var Prueba = {...req.body}
    dbOperations.createPruebas(Prueba).then(result => {
        res.json(result);
    })
})

app.post(api + '/test/:custId', (req, res) => {

    var custId = req.params.custId;
    var Prueba = {...req.body};
    dbOperations.updatePruebas(Prueba,custId).then(result => {
        res.json(result);
    })
})

app.delete(api + '/test/:custId', (req, res) => {

    
    dbOperations.deletePruebas(req.params.custId).then(result => {
        res.send(result);
    })
})

app.get('/*', function(req, res) {
    requestsHandler.testRequest(req, res);
});

app.listen(port, () => {
    console.log('Server Running: ' + port)
})