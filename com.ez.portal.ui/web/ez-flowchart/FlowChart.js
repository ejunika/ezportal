/**
 * 
 */
(function (ctx, fn) {
    'use strict';
    fn(ctx.ez = ctx.ez || {});
})(this, function(ez) {
    'use strict';
    
    var 
        connector       = Connector.prototype,
        socket          = Socket.prototype,
        inputSocket     = InputSocket.prototype,
        outputSocket    = OutputSocket.prototype,
        node            = Node.prototype,
        flowChart       = FlowChart.prototype,
        nodeCounter     = Node.counter = 0,
        iSocketCounter  = InputSocket.counter = 0,
        oSocketCounter  = OutputSocket.counter = 0
        ;
    
    /**
     * Connector constructor
     * */
    function Connector(data) {
        this.mData = data;
        this.mIsSelected = false;
    }
    
    /**
     * 
     * */
    connector.select = function() {
        var connector = this;
        connector.mIsSelected = true;
    };
    
    /**
     * 
     * */
    connector.deSelect = function() {
        var connector = this;
        connector.mIsSelected = false;
    };
    
    /**
     * 
     * */
    function Socket(data) {
        this.mRadius = 5;
        this.mStrokeColor = '#000';
        this.mStrokeWidth = 1;
        this.mData = data;
    }
    socket.getRadius = function () {
        return this.mRadius;
    };
    socket.getStrokeColor = function () {
        return this.mStrokeColor;
    };
    socket.getStrokeWidth = function () {
        return this.mStrokeWidth;
    };
    
    /**
     * 
     * */
    function InputSocket(data) {
        InputSocket.base.constructor.call(this, data);
        this.mId = data.id || this.getIncrementalId();
        this.mName = data.name || 'ISocket_' + this.mId;
        this.mCx = 0;
        this.mCy = 0;
    }
    inputSocket = extended(InputSocket, Socket);
    inputSocket.getIncrementalId = function () {
        return ++iSocketCounter;
    };
    
    /**
     * 
     * */
    function OutputSocket(data) {
        OutputSocket.base.constructor.call(this, data);
        this.mId = data.id || this.getIncrementalId();
        this.mName = data.name || 'OSocket_' + this.mId;
    }
    outputSocket = extended(OutputSocket, Socket);
    outputSocket.getIncrementalId = function () {
        return ++oSocketCounter;
    };
    
    /**
     * 
     * */
    function Node(data) {
        this.mInputSockets = this.createInputSockets(data.inputSockets);
        this.mOutputSockets = this.createOutputSockets(data.outputSockets);
        this.mId = data.id || this.getIncrementalId();
        this.mX = data.x || 0;
        this.mY = data.y || 0;
        this.mWidth = data.width || 100;
        this.mHeight = this.mWidth;
        this.mStrokeColor = data.strokeColor || '#000';
        this.mStrokeWidth = data.strokeWidth || '1';
        this.mName = data.name || 'Node_' + this.mId;
        this.mData = data;
    }
    
    /**
     * 
     * */
    node.getIncrementalId = function () {
        return ++nodeCounter;
    };
    
    /**
     * 
     * */
    node.createInputSockets = function (data) {
        var inputSockets = [];
        if (Array.isArray(data)) {
            for (var i = 0; i < data.length; i++) {
                inputSockets.push(new InputSocket(data[i]));
            }
        }
        return inputSockets;
    };
    
    /**
     * 
     * */
    node.createOutputSockets = function (data) {
        var outputSockets = [];
        if (Array.isArray(data)) {
            for (var i = 0; i < data.length; i++) {
                outputSockets.push(new OutputSocket(data[i]));
            }
        }
        return outputSockets;
    };
    
    /**
     * 
     * */
    node.getX = function () {
        return this.mX;
    };
    
    /**
     * 
     * */
    node.getY = function () {
        return this.mY;
    };
    
    /**
     * 
     * */
    node.getName = function () {
        return this.mName;
    };
    
    /**
     * 
     * */
    node.getWidth = function () {
        return this.mWidth;
    };
    
    /**
     * 
     * */
    node.getHeight = function () {
        return this.mHeight;
    };
    
    /**
     * 
     * */
    node.getStrokeColor = function () {
        return this.mStrokeColor;
    };
    
    /**
     * 
     * */
    node.getStrokeWidth = function () {
        return this.mStrokeWidth;
    };
    
    /**
     * 
     * */
    function FlowChart(data) {
        this.mNodes = this.createNodes(data.nodes);
        this.mConnectors = this.createConnectors(data.connectors);
        this.mData = data;
    }
    
    /**
     * 
     * */
    flowChart.createNodes = function (dataNodes) {
        var nodes = [], flowChart = this;
        if (Array.isArray(dataNodes)) {
            for (var i = 0; i < dataNodes.length; i++) {
                nodes.push(new Node(dataNodes[i]));
            }
        }
        return nodes;
    };
    
    /**
     * 
     * */
    flowChart.createConnectors = function (dataConnectors) {
        var connectors = [], flowChart = this;
        if (Array.isArray(dataConnectors)) {
            for (var i = 0; i < dataConnectors.length; i++) {
                connectors.push(new Connector(dataConnectors[i]));
            }
        }
    };
    
    /**
     * 
     * */
    function extended(child, parent) {
        function inherits(proto) {
            function F() {}
            F.prototype = proto;
            return new F();
        }
        child.prototype = inherits(parent.prototype);
        child.prototype.constructor = child;
        child.base = parent.prototype;
        return child.prototype;
    }
    
    ez.FlowChart = FlowChart;
});