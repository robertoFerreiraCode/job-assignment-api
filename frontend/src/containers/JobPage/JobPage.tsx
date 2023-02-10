import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from 'axios';
import { Temp } from "../../utilities/types";
import StyledContainer from "../../styles/Container.styled";

const JobPage = () => {
    const location = useLocation()
    const { job } = location.state
    const [temps, setTemps] = useState<Temp[]>([]);
    const [selectedTempId, setSelectedTempId] = useState("");
    const navigate = useNavigate();
    
    useEffect(() => {
        getTemps();
    }, [])

    const getTemps = async () => {
        const response = await axios.get("/api/temps?jobID=" + job.id);
        console.log(job.id);
        const data: Temp[] = response.data;
        setTemps(data);
    }

    const handleTempAssign = async () => {
        const reqBody = {
            tempID: selectedTempId,
        };
        const response = await axios.patch("/api/jobs/" + job.id, reqBody);
        const data = response.data;
        console.log(data);
        navigate("/");
    }

    return (
        <StyledContainer>
            <div className="container">
                <h2>{job.name}</h2>
                <h3 className="padded">Start Date: {job.startDate}</h3>
                <h3 className="padded">End Date: {job.endDate}</h3>
                <div>
                    <div className="form-group">
                        <h3 className="margin-right">Assign Temp:</h3>
                        <select 
                            name="tempSelect" 
                            onChange={(e) => setSelectedTempId(e.target.value)}
                        >
                        {temps.map((temp, index) => {
                            return <option 
                                key={index} 
                                value={temp.id}
                                >
                                    {temp.firstName + " " + temp.lastName}
                                </option>
                        })}
                        </select>
                    </div>
                    <button type="button" id="submit" onClick={() => handleTempAssign()}>Assign</button>
                </div>
            </div>
        </StyledContainer>
    )
}

export default JobPage;