
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import Jobs from "../Jobs";
import Login from "../Login"

const Home = () => {
    const navigate = useNavigate();
    useEffect(() => {
        // if (id == 0) navigate("/login");
        if (sessionStorage.length == 0) navigate("/login");
    }, [])
    return (
        <div>
            <h1>Job Assignment Portal</h1>
        </div>
    )
}

export default Home;