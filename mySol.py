# Implement the class below, keeping the constructor's signature unchanged; it should take no arguments.
import json

class MarkingPositionMonitor:
    def __init__(self):
        # record marking position for every symbol
        self.markingPos = {}
        # record order information to use for roll-back
        self.order = {}
        
    def on_event(self, message):
        # loads json data
        data = json.loads(message)
        event_type = data["type"]
        # invoke respect method according to event_type
        if event_type == "NEW":
            side = data["side"]
            if side == "SELL":
                return self.new_order(data, False)
            if side == "BUY":
                return self.new_order(data, True)
        elif event_type == "ORDER_ACK":
            return self.order_ack(data)
        elif event_type == "ORDER_REJECT":
            return self.order_reject(data)
        elif event_type == "CANCEL":
            return self.cancel(data)
        elif event_type == "CANCEL_ACK":
            return self.cancel_accept(data)
        elif event_type == "CANCEL_REJECT":
            return self.cancel_reject(data)
        elif event_type == "FILL":
            return self.fill(data)

    def order_ack(self, data):
        order_id = data["order_id"]
        symbol = self.order[order_id]["symbol"]
        return self.markingPos[symbol]

    def order_reject(self, data):
        order_id = data["order_id"]
        symbol = self.order[order_id]["symbol"]
        
        if order_id in self.order:
            if "reject" not in self.order[order_id]:
                self.order[order_id]["reject"] = True
                # only can change marking position which increased by SELL
                if self.order[order_id]["side"] == "SELL":
                    quantity = self.order[order_id]["remaining"]
                    self.markingPos[symbol] += quantity
            return self.markingPos[symbol]
        else:
            return 0

    # does not change marking position
    def cancel(self, data):
        order_id = data["order_id"]
        if order_id in self.order:
            symbol = self.order[order_id]["symbol"]
            return self.markingPos[symbol]
        else:
            return 0

    def new_order(self, data, buy):
        symbol = data["symbol"]
        quantity = data["quantity"]
        orderId = data["order_id"]
        # what if invalid input such as orderId already exist?
        # if orderId not in self.order:
        if not buy:
            self.order[orderId] = {"symbol": symbol, "side": "SELL", "remaining": quantity, "filled": 0}
            # use dictionary.get() to avoid key error
            self.markingPos[symbol] = self.markingPos.get(symbol, 0) - quantity
        else:
            self.order[orderId] = {"symbol": symbol, "side": "BUY", "remaining": quantity, "filled": 0}
            self.markingPos[symbol] = self.markingPos.get(symbol, 0)
        return self.markingPos[symbol]
    
    def cancel_accept(self, data):
        order_id = data["order_id"]
        symbol = self.order[order_id]["symbol"]
        quantity = self.order[order_id]["remaining"]
        if "cancel" not in self.order[order_id]:
            self.order[order_id]["cancel"] = True
            if self.order[order_id]["side"] == "SELL":
                self.markingPos[symbol] += quantity
                
        return self.markingPos[symbol]

    def cancel_reject(self, data):
        order_id = data["order_id"]
        if order_id in self.order:
            symbol = self.order[order_id]["symbol"]
            return self.markingPos[symbol]
        else:
            return 0

    def fill(self, data):
        order_id = data["order_id"]
        side = self.order[order_id]["side"]
        filled = data["filled_quantity"]
        symbol = self.order[order_id]["symbol"]
        if order_id in self.order:
            # does not check if filled > remaining
            if filled > self.order[order_id]["remaining"]:
                filled = self.order[order_id]["remaining"]
                self.order[order_id]["filled"] += filled
                self.order[order_id]["remaining"] = 0
            else:
                self.order[order_id]["remaining"] -= filled
                self.order[order_id]["filled"] += filled
            # only can change marking position which increased by SELL
            if side == "BUY":
                self.markingPos[symbol] += filled
            return self.markingPos[symbol]
        else:
            return 0