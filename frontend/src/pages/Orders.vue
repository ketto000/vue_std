<template>
    <div class="orders">
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">주문자명</th>
                    <th scope="col">주소</th>
                    <th scope="col">결제수단</th>
                    <th scope="col">구입항목</th>
                </tr>
                </thead>
                <tbody class="table-group-divider">

                <tr v-for="(o, idx) in state.orders" :key="idx">
                    <th scope="row">{{ state.orders.length - idx }}</th>
                    <td>{{ o.name }}</td>
                    <td>{{ o.address }}</td>
                    <td>{{ o.payment }}</td>
                    <td>
                        <div v-for="(i, idx2) in o.items" :key="idx2">
                            {{ i.name }}

                        </div>

                    </td>
                </tr>


                </tbody>
            </table>
        </div>

    </div>

</template>

<script>

    import axios from "axios";
    import {reactive, computed} from "vue";
    import Card from "@/components/Card";
    import lib from "../script/lib";

    export default {
        name: "Orders",
        components: {Card},

        setup() {
            const state = reactive({
                orders: [],

            });

            axios.get("/api/orders").then(({data})=>{

                // state.orders=data;
                state.orders = [];

                for( let d of data){
                    d.items = JSON.parse(d.items);
                    state.orders.push(d);
                }

            });



            return {lib, state};
        }
    }

</script>


<style scoped>
    .col-12, .mb-3, .col-md-6 {
        text-align: left;
    }

    .form-check {
        text-align: left;
    }
</style>