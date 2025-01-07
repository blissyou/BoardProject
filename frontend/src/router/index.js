import {createWebHistory,createRouter} from "vue-router";
import HelloWorld from "../components/HelloWorld.vue"
import CreatePost from "@/components/CreatePost.vue";


const routes = [{
        path:"/",
        component:HelloWorld,
        name:"home"
    },
    {
        path:"/post",
        component: CreatePost,
        name:"post"
    }

]


const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router;