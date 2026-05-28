import { useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";

function Login() {

  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  // LOGIN FUNCTION
  const handleLogin = async (e) => {

    e.preventDefault();

    try {

      console.log("Sending Login Request...");

      const response = await API.post(
        "/auth/login",
        {
          username: username,
          password: password,
        }
      );

      console.log(
        "LOGIN SUCCESS:",
        response.data
      );

      // SAVE TOKEN
      localStorage.setItem(
        "token",
        response.data.token
      );

      // SUCCESS ALERT
      alert("Login Successful ✅");

      // REDIRECT
      navigate("/dashboard");

    } catch (error) {

      console.log(
        "FULL LOGIN ERROR:",
        error
      );

      // SHOW ACTUAL ERROR
      if (error.response) {

        console.log(
          "STATUS:",
          error.response.status
        );

        console.log(
          "DATA:",
          error.response.data
        );

        alert(
          "Backend Error: "
          + JSON.stringify(error.response.data)
        );

      } else {

        alert("Network Error");
      }
    }
  };

  return (

    <div className="min-h-screen flex items-center justify-center bg-gray-100">

      <div className="bg-white p-10 rounded-2xl shadow-xl w-96">

        <h1 className="text-3xl font-bold mb-8 text-center">
          DevOps Login 🚀
        </h1>

        <form onSubmit={handleLogin}>

          {/* USERNAME */}
          <input
            type="text"
            placeholder="Username"
            className="w-full border p-3 rounded-lg mb-4"
            value={username}
            onChange={(e) =>
              setUsername(e.target.value)
            }
            required
          />

          {/* PASSWORD */}
          <input
            type="password"
            placeholder="Password"
            className="w-full border p-3 rounded-lg mb-6"
            value={password}
            onChange={(e) =>
              setPassword(e.target.value)
            }
            required
          />

          {/* LOGIN BUTTON */}
          <button
            type="submit"
            className="w-full bg-black text-white py-3 rounded-lg hover:bg-gray-800 transition"
          >
            Login
          </button>

        </form>

      </div>

    </div>
  );
}

export default Login;