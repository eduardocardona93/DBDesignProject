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


app.get(api + '/salesPerson', (req, res) => {
    dbOperations.getAllSalesPersons().then(result => {
        res.send(result);
    })
})
app.get(api + '/salesPerson/:salesPersonId', (req, res) => {
    var salesPersonId = req.params.salesPersonId;
    dbOperations.getOneSalesPerson(salesPersonId).then(result => {
        res.json(result);
    });
})
app.get(api + '/salesPerson/login', (req, res) => {
    var email = req.header.email;
    var password = req.header.password;
    dbOperations.getUserSalesPerson(email,password).then(result => {
        res.json(result);
    })
})
app.post(api + '/salesPerson', (req, res) => {
    var SalesPersonData = {...req.body}
    dbOperations.createSalesPerson(SalesPersonData).then(result => {
        res.json(result);
    })
})
app.post(api + '/salesPerson/:salesPersonId', (req, res) => {
    var salesPersonId = req.params.salesPersonId;
    var SalesPersonData = {...req.body};
    dbOperations.updateSalesPerson(SalesPersonData,salesPersonId).then(result => {
        res.json(result);
    })
})
app.delete(api + '/salesPerson/:salesPersonId', (req, res) => {
    dbOperations.deleteSalesPerson(req.params.salesPersonId).then(result => {
        res.send(result);
    })
})

app.get('/*', function(req, res) {
    requestsHandler.testRequest(req, res);
});

app.listen(port, () => {
    console.log('Server Running: ' + port)
})