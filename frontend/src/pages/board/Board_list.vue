<template>
    <main>

        <div class="album py-5 bg-light">
            <div class="container">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

                    <div class="col" v-for="(item, idx) in state.items" :key="idx">
                        <!--<div class="col" v-for="i in 12" key="i">-->
                        <Card :item="item"/>
                    </div>
                </div>
            </div>
        </div>


        <div id="page_area">
            <span v-if="state.temp >= 1">
                <span v-if="(state.now_page) >=1 && (state.now_page) <= state.page_scale"></span>
                <span v-else>
                    <span @click="go_pages(1)" class="page_link arrow">◀◀</span>
                    <span @click="go_pages(state.temp - state.page_scale)" class="page_link">◀</span>
                </span>
            </span>
                <span v-for="n in state.page_scale">
                <span v-if="state.temp + (n-1) > state.totalPages"></span>
                    <span v-else>
                        <span v-if="state.now_page  == (state.temp + (n -1) )">
                            <span class="page_link now_page"> {{ state.temp + (n-1)}}</span>
                        </span>
                        <span v-else>
                            <span class="page_link" @click="go_pages(state.temp + (n-1))">{{ state.temp + (n-1) }}</span>
                        </span>
                    </span>
                </span>
            <span v-if="state.temp + state.page_scale <= state.totalPages">
                    <span @click="go_pages(state.temp + state.page_scale)" class="page_link">▶</span>
                    <span @click="go_pages(state.totalPages)" class="page_link arrow">▶▶</span>
            </span>
        </div>
    </main>
</template>
<script>

    import Card from '@/components/Card.vue';
    import axios from "axios";
    import {reactive} from "vue";

    export default {
        name: "Board",
        components: {Card},
        setup() {
            const state = reactive({
                items: [],
                getPage: 0,
                searchKeyword: '',
                temp: 0,
                totalPages: 0,
                size: 0,
                totalElements: 0,
                page_scale: 3,
                now_page: 0,
            });


            const load = () => {

                const urlParameter = window.location;
                const url = new URL(urlParameter);
                const urlParams = url.searchParams;
                const get_page = urlParams.get('page');
                const get_keyword = urlParams.get('searchKeyword');

                state.getPage = get_page - 1;
                state.searchKeyword = get_keyword;


                axios({
                    url: '/api/board/list', // 통신할 웹문서
                    method: 'get', // 통신할 방식
                    params: {
                        page: state.getPage, searchKeyword: state.searchKeyword
                    },
                }).then(({data}) => {
                    console.log(data);
                    state.items = data.content;
                    state.size = data.size;
                    state.totalElements = data.totalElements;
                    state.now_page=data.number+1;
                    makePaging();
                });
            }

            load();


            function makePaging() {
                const results_per_pages = state.size;
                const num_pages = state.totalPages;
                const page_scale = state.page_scale;
                state.totalPages =  Math.ceil(state.totalElements / state.size);
                state.temp = Math.floor((state.now_page -1) / page_scale) * page_scale + 1;
            }


            function go_pages(val) {
                state.getPage = val;
                location.href = "/board?page=" + val + "&searchKeyword=";
            }
            function set_pages(val) {
                state.getPage = val;
                location.href = "/board?page=" + val + "&searchKeyword=";
            }

            return {state, set_pages, go_pages};
        }
    }


</script>
<style scoped>
    .page_link{
        border: 1px solid #64c4b4;
        width: 30px;
        height: 30px;
        border-radius: 5px;
        display: inline-block;
        text-align: center;
        vertical-align: middle;
        font-weight: 400;
        line-height: 30px;
        cursor: pointer;
        margin-right: 3px;
    }
     .arrow{
         letter-spacing: -3px;
     }
     .now_page{
         background-color: #0dcaf0;
         color:#fff;
     }

    #page_area{

    }

</style>
