import {createRouter, createWebHistory} from "vue-router";
import Login from "@/pages/Login";
import Home from "@/pages/Home";
import Cart from "@/pages/Cart";
import Order from "../pages/Order";
import Orders from "../pages/Orders";
import Board from "../pages/board/Board_list";


const routes = [
    {path: '/', component: Home},
    {path:'/login', component: Login},
    {path:'/cart', component: Cart},
    {path:'/order', component: Order},
    {path:'/orders', component: Orders},
    {path:'/board', component: Board},

];

const router = createRouter({
    history : createWebHistory(),
    routes
});

export default router;