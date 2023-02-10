import styled from "styled-components";

const StyledContainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin-top: 5vh;
    
    .container {
        padding: 0px 70px;
        box-shadow: 0 1px 8px #ddd;
        border: solid #f7f7f9;
        background-color: white;
        border-radius: 10px;
    }

    h1 {
        font-size: 50px;
        margin: 20px 0;
    }

    h2 {
        font-size: 25px;
        padding: 20px;
    }

    h3 {
        font-size: 20px;
        &.padded {
            padding: 10px;
        }
        &.margin-right {
            margin-right: 10px;
        }
    }

    p {
        
    }

    .form-group {
        display: flex;
        align-items: flex-start;
        padding: 15px;
        &-column {
            flex-direction: column; 
            padding-bottom: 15px;
        }
    }

    ul {
        display: flex;
        justify-content: space-between;
        flex-direction: column;
        padding: 10px;
    }

    li {
        list-style: none;
        border-top: solid 1px #ddd;
        padding: 40px 80px;
    }

    li:hover {
        background-color: #2d9ace;
        transition: all 0.3s ease 0s;
    }

    a {
        text-decoration: none;
        color: #212120;
    }

    button {
        background-color: #2d9ace;
        color: #fff;
        margin-bottom: 20px;
    }

`
export default StyledContainer;