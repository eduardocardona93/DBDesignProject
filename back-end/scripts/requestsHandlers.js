'use strict';
var request = require('request');

let testRequest = (req, res) => {
    res.send('OK SERVICE');
}

module.exports = {
    testRequest
}