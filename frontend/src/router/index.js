import {createWebHistory,createRouter} from "vue-router";
import BoardPostList from "@/views/board/BoardPostList.vue";
import BoardDetail from "@/views/board/BoardDetail.vue";
import BoardWrite from "@/views/board/BoardWrite.vue";
import BoardHome from "@/views/board/BoardHome.vue";
import BoardList from "@/views/board/boardList.vue";
import BoardAbout from "@/views/board/pageAbout.vue";




const router = createRouter({
    history: createWebHistory(),
    routes : [
        {
            path:"/",
            name:'Board home',
            component: BoardHome
        },
        {
            path: "/board",
            name: 'Board',
            component: BoardList
        },
        {
            path:"/about",
            name: 'About',
            component: BoardAbout
        },
        {
            path: "/post/:id",
            name: 'post',
            component: BoardPostList
        },
        {
            path: "/detail",
            name: 'Detail',
            component: BoardDetail
        },
        {
            path: "/write",
            name: 'Write',
            component: BoardWrite
        }
    ]
})

export default router;