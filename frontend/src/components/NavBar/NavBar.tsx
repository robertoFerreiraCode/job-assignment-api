import { Link } from "react-router-dom";
import { StyledNav } from "../../styles/NavBar.styled";

const NavBar = () => {
    return (
        <header>
            <StyledNav>
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                    <li>
                        <Link to="/jobs">Jobs</Link>
                    </li>
                    <li>
                        <Link to="/temps">Temps</Link>
                    </li>
                    <li>
                        <Link to="/profile">Profile</Link>
                    </li>
                </ul>
            </StyledNav>
        </header>
    )
}

export default NavBar;