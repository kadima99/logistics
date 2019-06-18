package com.lnsf.logistics.route;

import com.lnsf.logistics.entity.Orders;
import com.lnsf.logistics.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class RouteWeight {

    @Autowired
    private WarehouseService warehouseService;

    private Weight weight;

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public class Weight{
        private Integer startId;
        private Integer endId;
        private List<Integer> summary;
        private Integer distance;//距离
        private Integer done;//订单数
        private Integer weight;//权值

        public Integer getStartId() {
            return startId;
        }

        public void setStartId(Integer startId) {
            this.startId = startId;
        }

        public Integer getEndId() {
            return endId;
        }

        public void setEndId(Integer endId) {
            this.endId = endId;
        }

        public List<Integer> getSummary() {
            return summary;
        }

        public void setSummary(List<Integer> summary) {
            this.summary = summary;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public Integer getDone() {
            return done;
        }

        public void setDone(Integer done) {
            this.done = done;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public  Weight(Integer distance, Integer done){
            this.distance = distance;
            this.done = done;
            this.weight = distance/done;
        }

        public Weight() {
        }
    }

    public List<Weight> getWeight(List<Orders> orders){
        List<Weight> weights =new ArrayList<>();
//        for(int i = 0;i<orders.size();i++){
//           Weight weight = new Weight();
//           weight.setOrderId(orders.get(i).getOrderId());
//        }
        return weights;
    }

    public static void main(String[] args) {

    }



}
