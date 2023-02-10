import axios from "axios";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Temp } from "../../utilities/types";
import StyledContainer from "../../styles/Container.styled";

const Temps = () => {
    const [temps, setTemps] = useState<Temp[]>([]);
    
    useEffect(() => {
        getTemps();
    }, [])

    const getTemps = async () => {
        const response = await axios.get("/api/temps/");
        const data: Temp[] = response.data;
        setTemps(data);
    }

    return (
        <StyledContainer>
            <div className="container">
                <h1>Temps</h1>
                <ul>
                    {temps.map((temp, index) => {
                        return <Link 
                            to={"/temps/" + temp.id}
                            state={{ temp: temp }}>
                                <li key={index}>
                                    {temp.firstName + " " + temp.lastName}
                                </li>
                            </Link>
                    })}
                </ul>
            </div>
        </StyledContainer>
    )
}

export default Temps;