import styled from "styled-components";

export const StyledNav = styled.nav`
    display: flex;
    justify-content: flex-end;
    align-items: center;
    padding: 20px 50px;
    background-color: #212120;
    box-shadow: 0 1px 8px #ddd;

    ul {
        display: flex;
        justify-content: space-between;
        flex-direction: row;
        width: 50%;
    }

    li {
        list-style: none;
        display: inline-block;
        padding: 0 20px;
    }

    a {
        text-decoration: none;
        color: #f4f4f4;
        transition: all 0.3s ease 0s;
    }
    
    a:hover {
        color: #2d9ace;
    }
`;

