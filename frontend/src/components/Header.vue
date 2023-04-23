<template>

    <header>
        <div class="collapse bg-dark" id="navbarHeader">
            <div class="container">
                <div class="row left">

                    <div class="col-sm-4 offset-md-1 py-4">

                        <ul class="list-unstyled">
                            <li>
                                <router-link to="/" class="text-white">메인 화면</router-link>
                            </li>
                            <li>
                                <router-link to="/board" class="text-white">게시판</router-link>
                            </li>


                            <li>
                                <router-link to="/login" class="text-white" v-if="!$store.state.account.id">로그인</router-link>
                                <router-link to="/login" class="text-white" @click="logout" v-else>로그아웃</router-link>
                            </li>
                            <li>
                                 <router-link to="/orders" class="text-white" v-if="$store.state.account.id">주문내역</router-link>
                            </li>

                            {{msg}}
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="navbar navbar-dark bg-dark shadow-sm">

            <div class="container">

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarHeader"
                        aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>


                </button>
                <a href="#" class="navbar-brand d-flex align-items-center">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor"
                         stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="me-2"
                         viewBox="0 0 24 24">
                        <path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/>
                        <circle cx="12" cy="13" r="4"/>
                    </svg>
                    <router-link to="/" class="cart">  <strong>Galley</strong></router-link>

                </a>
                <router-link to="/cart" class="text-white"><i class="fa fa-shopping-cart" aria-hidden="true"></i></router-link>




            </div>

        </div>
    </header>
</template>

<script>
    import store from "../script/store";

    import router from "../script/router";
    import axios from "axios";


    export default {
        name: 'Header',
        setup() {
            const logout = () => {

                axios.post("/api/account/logout").then(() => {

                    store.commit('setAccount', 0);
                    sessionStorage.removeItem("id");
                    router.push({path: "/"});
                })

            }

            return {logout};
        }
    }
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
    header .navbar .cart{
        margin-left:auto;
        text-decoration-line: none;
    }

</style>
