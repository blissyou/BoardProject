import {createWebHistory,createRouter} from "vue-router";
import BoardList from "@/views/board/BoardList.vue";
import BoardDetail from "@/views/board/BoardDetail.vue";
import BoardWrite from "@/views/board/BoardWrite.vue";
import BoardHome from "@/views/board/BoardHome.vue";

const routes = [
    {
        path:"/",
        name:'Board home',
        component: BoardHome
    },
    {
        path:"/about",
        name: 'About',
        component: ()=>import('../views/board/pageAbout.vue')
    },
    {
        path: "/board",
        name: 'Board',
        component: BoardList
    },
    {
        path: "/Detail",
        name: 'Detail',
        component: BoardDetail
    },
    {
        path: "/Write",
        name: 'Write',
        component: BoardWrite
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router;