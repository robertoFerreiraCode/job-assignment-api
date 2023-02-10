import styled from "styled-components";

export const StyledForm = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    height: 75vh;
    
    .container {
        padding: 70px;
        padding-top: 10px;
        box-shadow: 0 1px 8px #ddd;
        border: solid #f7f7f9;
        background-color: white;
        border-radius: 10px;
    }

    h2 {
        font-size: 25px;
        padding: 20px;
    }

    input {
        border: 1px solid #ddd;
        border-radius: 4px;
        color: #66615b;
        box-shadow: none;
        transition: color .3s ease-in-out,border-color .3s ease-in-out,background-color .3s ease-in-out;
    }
    
    .form-group {
        display: flex;
        align-items: flex-start;
        flex-direction: column;
        margin-bottom: 20px;
    }

    button {
        background-color: #2d9ace;
        color: #fff;
    }
`;

