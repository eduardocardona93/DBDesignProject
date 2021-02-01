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

app.get(api + '/clients', (req, res) => {
    dbOperations.getAllClients().then(result => {
        res.send(result);
    })
})
app.get(api + '/clients/:clientId', (req, res) => {

    var clientId = req.params.clientId;
    dbOperations.getOneClient(clientId).then(result => {
        res.json(result);
    })
})

app.post(api + '/clients', (req, res) => {
    var ClientData = {...req.body}
    dbOperations.createClients(ClientData).then(result => {
        res.json(result);
    })
})

app.post(api + '/clients/:clientId', (req, res) => {
    var clientId = req.params.clientId;
    var ClientData = {...req.body};
    dbOperations.updateClients(ClientData,clientId).then(result => {
        res.json(result);
    })
})

app.delete(api + '/clients/:clientId', (req, res) => {
    dbOperations.deleteClients(req.params.clientId).then(result => {
        res.send(result);
    })
})

app.get('/*', function(req, res) {
    requestsHandler.testRequest(req, res);
});

app.listen(port, () => {
    console.log('Server Running: ' + port)
})