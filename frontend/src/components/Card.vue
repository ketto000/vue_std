<template>


    <div class="col">
        <div class="card shadow-sm">
            <span class="img" :style="{backgroundImage: `url(${item.imgPath})` }"/>
            <!--<img :src="item.imgPath"/>-->

            <div class="card-body">
                <p class="card-text">
                    {{item.name}}
                    <span class="discount badge bg-danger">
                        {{item.discountPer}}%
                    </span>
                </p>

                <div class="d-flex justify-content-between align-items-center">
                    <button class="btn btn-primary" @click="addToCart(item.id)">

                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>

                    </button>


                    <small class="price text-muted">
                        {{lib.getNumberFormatted(item.price)}}원
                    </small>

                    <small class="real text-danger">
                        {{lib.getNumberFormatted( item.price - ((item.price * item.discountPer) / 100)) }}원
                    </small>
                </div>
            </div>
        </div>
    </div>


</template>

<script>
    import lib from "@/script/lib";
    import axios from "axios";


    export default {
        name: 'Card',
        props: {
            item: Object
        },
        setup() {
            const addToCart = (itemId)=> {
                axios.post(`/api/cart/items/${itemId}`).then(() => {
                    // withCredentials: true, // 401에러 ???
                    console.log("success")
                })
         };
          return {lib, addToCart};
    }

    }
</script>
<style scoped>
    .card .img {
        display: inline-block;
        width: 100%;
        height: 250px;
        background-size: cover;
        background-position: top center;
    }

    .card .card-body .price {
        text-decoration: line-through;
    }

    .card-text {
        text-align: left;
    }
</style>