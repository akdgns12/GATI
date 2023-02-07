import { React } from "react";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, Button, Container, TextField } from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from 'react-redux';
import { doLoginUser } from "../../store/User/user";

const contStyle = css`
  width: 300px;
  background-color: rgba(255, 255, 255, 0.75);
  margin: 0 auto;
  margin-top: 50vh;
  border-radius: 10px;
  .input-form {
    widht: 80%;
    border-radius: 10px;
  }
  .login-btn {
    width: 100%;
    border-radius: 5px;
    margin-top: 10px;
  }
  .btn-group {
    margin-top: 15px;
    width: 100%;
    border-style: solid;
    border-width: 1px;
    border-radius: 5px;
    border-color: #c8c8c8;
  }
  .router-link {
    box-sizing: border-box;
    margin: 10px;
    display: inline-block;
    color: gray;
    text-decoration: none;
    div {
      border-style: solid;
      border-width: 0px;
      border-color: #ff0000;
    }
  }
`;

const LoginFrom = () => {
  const navigate = useNavigate();
  const { loginUser, logIn } = useSelector((state) => state.user);
  const dispatch = useDispatch();

  function handleLogin(event) {
    event.preventDefault();
    const user = {
      userId: event.target.id.value,
      password: event.target.pw.value,
    };
    dispatch(doLoginUser(user))
      .then((data) => {
        // console.log(data);
        // console.log(data.payload);
        if (data.payload.msg === "success") {
          alert("Hello");
          navigate("/");
        }
        else {
          console.log("failed to login");
          alert("Incorrect ID or PW");
        }
      })
      .catch((error) => console.log(error));

  }

  return (
    <Container css={contStyle}>
      <Box component="form" onSubmit={handleLogin}>
        <TextField
          margin="normal"
          required
          fullWidth
          label="ID"
          size="small"
          name="id"
          autoFocus
        />
        <TextField
          margin="normal"
          required
          fullWidth
          label="PASSWORD"
          type="password"
          size="small"
          name="pw"
        />
        <Button
          type="submit"
          variant="outlined"
          color="inherit"
          className="login-btn"
        >
          Login
        </Button>
      </Box>
      <Box className="btn-group">
        <Link to="/login/signup" className="router-link">
          <div>회원가입</div>
        </Link>
        <Link to="/login/findID" className="router-link">
          <div>ID 찾기</div>
        </Link>
        <Link to="/login/findPW" className="router-link">
          <div>PW 찾기</div>
        </Link>
      </Box>
    </Container>
  );
};

export default LoginFrom;
