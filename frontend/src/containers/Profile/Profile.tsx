import axios from "axios";
import { useEffect, useState } from "react";
import { Temp } from "../../utilities/types";
import { StyledForm } from "../../styles/Form.styled";
import StyledContainer from "../../styles/Container.styled";

const Profile = () => {
    const [temp, setTemp] = useState<Temp>();
    
    const [firstName, setFirstName] = useState<any>("");
    const [lastName, setLastName] = useState<any>("");
    const [username, setUsername] = useState<any>("");
    const [password, setPassword] = useState<any>("");

    useEffect(() => {
        getTemp();
    }, []);

    useEffect(() => {
        setFirstName(temp?.firstName);
        setLastName(temp?.lastName);
        setUsername(temp?.username);
        setPassword(temp?.password);
    }, [temp])

    const getTemp = async () => {
        const id = sessionStorage.getItem("id");
        console.log(id);
        const response = await axios.get("/api/temps/" + id);
        const data: Temp = response.data;
        setTemp(data);
    }

    const handleSubmitDetails = async () => {
        const reqBody = {
            firstName: firstName,
            lastName: lastName,
            username: username,
            password: password,
        };
        const response = await axios.patch("/api/temps/edit/" + temp?.id, reqBody);
        const data = response.data;
        console.log(data);
    }

    // const handleChange = (event : React.ChangeEvent<HTMLInputElement>) => {
    //     const name = event.target.name;
    //     const value = event.target.value;
    //     setTemp(values => ({...values, [name]: value}))
    //   }

    return (
        <StyledForm>
            <div className="container">
                <h2>Profile</h2>
                <div className="form-group">
                    <label>First Name:</label>
                    <input 
                        id="firstName" 
                        placeholder={temp?.firstName} 
                        onChange={(e) => setFirstName(e.target.value)}
                        // onChange={(e) => handleChange(e)}
                    ></input>
                </div>
                <div className="form-group">
                    <label>Last Name:</label>
                    <input 
                        id="lastName" 
                        placeholder={temp?.lastName}
                        onChange={(e) => setLastName(e.target.value)}
                    ></input>
                </div>
                <div className="form-group">
                    <label>Username:</label>
                    <input 
                        id="username" 
                        placeholder={temp?.username}
                        onChange={(e) => setUsername(e.target.value)}
                    ></input>
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input 
                        id="password" 
                        placeholder={temp?.password}
                        onChange={(e) => setPassword(e.target.value)}
                    ></input>
                </div>
                <button id="submit" type="submit" onClick={handleSubmitDetails}>Submit</button>
            </div>
        </StyledForm>
    )
}

export default Profile;