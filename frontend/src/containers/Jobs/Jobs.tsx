import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Job } from "../../utilities/types";
import StyledContainer from "../../styles/Container.styled";

const Jobs = () => {
    const [jobs, setJobs] = useState<Job[]>([]);
    
    useEffect(() => {
        getJobs();
    }, [])

    const getJobs = async () => {
        const response = await fetch("/api/jobs?assigned=false", {
            headers: {
                "Content-Type": "application/json", 
            }, 
            method: "get"
        })
        const data: Job[] = await response.json();
        setJobs(data);
    }

    return (
        <StyledContainer>
            <div className="container">
                <h1>Jobs</h1>
                <ul>
                    {jobs.map((job, index) => {
                        return <Link 
                            to={"/jobs/" + job.id}
                            state={{ job: job }}>
                                <li key={index}>
                                    {job.name}
                                </li>
                            </Link>
                    })}
                </ul>
            </div>
        </StyledContainer>
    )
}

export default Jobs;