import { useEffect, useState } from "react";
import API from "../services/api";

function Dashboard() {

  // PIPELINE DATA
  const [pipelines, setPipelines] = useState([]);

  // FORM DATA
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [status, setStatus] = useState("");

  // EDIT MODE
  const [editingId, setEditingId] = useState(null);

  // FETCH DATA ON LOAD
  useEffect(() => {
    fetchPipelines();
  }, []);

  // FETCH PIPELINES
  const fetchPipelines = async () => {

    try {

      const response =
        await API.get("/pipelines");

      setPipelines(response.data);

    } catch (error) {

      console.log(
        "Error fetching pipelines",
        error
      );
    }
  };

  // CREATE PIPELINE
  const createPipeline = async (e) => {

    e.preventDefault();

    try {

      await API.post("/pipelines", {
        name,
        description,
        status,
      });

      // REFRESH TABLE
      fetchPipelines();

      // CLEAR FORM
      clearForm();

    } catch (error) {

      console.log(
        "Error creating pipeline",
        error
      );
    }
  };

  // UPDATE PIPELINE
  const updatePipeline = async (e) => {

    e.preventDefault();

    try {

      await API.put(
        `/pipelines/${editingId}`,
        {
          name,
          description,
          status,
        }
      );

      // REFRESH TABLE
      fetchPipelines();

      // CLEAR FORM
      clearForm();

    } catch (error) {

      console.log(
        "Error updating pipeline",
        error
      );
    }
  };

  // DELETE PIPELINE
  const deletePipeline = async (id) => {

    try {

      await API.delete(
        `/pipelines/${id}`
      );

      // REFRESH TABLE
      fetchPipelines();

    } catch (error) {

      console.log(
        "Error deleting pipeline",
        error
      );
    }
  };

  // EDIT PIPELINE
  const editPipeline = (pipeline) => {

    setEditingId(pipeline.id);

    setName(pipeline.name);

    setDescription(
      pipeline.description
    );

    setStatus(pipeline.status);
  };

  // CLEAR FORM
  const clearForm = () => {

    setName("");
    setDescription("");
    setStatus("");

    setEditingId(null);
  };

  // COUNTS
  const totalPipelines =
    pipelines.length;

  const successfulDeployments =
    pipelines.filter(
      (pipeline) =>
        pipeline.status === "SUCCESS"
    ).length;

  const failedDeployments =
    pipelines.filter(
      (pipeline) =>
        pipeline.status === "FAILED"
    ).length;

  // LOGOUT
  const handleLogout = () => {

    localStorage.removeItem(
      "token"
    );

    window.location.href = "/";
  };

  return (

    <div className="min-h-screen bg-gray-100">

      {/* NAVBAR */}
      <nav className="bg-black text-white px-8 py-4 flex justify-between items-center shadow-lg">

        <h1 className="text-2xl font-bold">
          DevOps Tracker 🚀
        </h1>

        <button
          onClick={handleLogout}
          className="bg-white text-black px-4 py-2 rounded-lg font-semibold"
        >
          Logout
        </button>

      </nav>

      {/* MAIN CONTENT */}
      <div className="p-8">

        <h2 className="text-4xl font-bold mb-8">
          Dashboard
        </h2>

        {/* DASHBOARD CARDS */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">

          {/* TOTAL */}
          <div className="bg-white p-6 rounded-2xl shadow-lg">

            <h3 className="text-xl font-semibold">
              Total Pipelines
            </h3>

            <p className="text-5xl font-bold mt-4 text-blue-600">
              {totalPipelines}
            </p>

          </div>

          {/* SUCCESS */}
          <div className="bg-white p-6 rounded-2xl shadow-lg">

            <h3 className="text-xl font-semibold">
              Successful Deployments
            </h3>

            <p className="text-5xl font-bold mt-4 text-green-600">
              {successfulDeployments}
            </p>

          </div>

          {/* FAILED */}
          <div className="bg-white p-6 rounded-2xl shadow-lg">

            <h3 className="text-xl font-semibold">
              Failed Deployments
            </h3>

            <p className="text-5xl font-bold mt-4 text-red-600">
              {failedDeployments}
            </p>

          </div>

        </div>

        {/* FORM */}
        <div className="bg-white rounded-2xl shadow-lg p-6 mb-10">

          <h2 className="text-2xl font-bold mb-6">

            {editingId
              ? "Update Pipeline"
              : "Create Pipeline"}

          </h2>

          <form
            onSubmit={
              editingId
                ? updatePipeline
                : createPipeline
            }
            className="grid grid-cols-1 md:grid-cols-4 gap-4"
          >

            {/* NAME */}
            <input
              type="text"
              placeholder="Pipeline Name"
              className="border p-3 rounded-lg"
              value={name}
              onChange={(e) =>
                setName(e.target.value)
              }
              required
            />

            {/* DESCRIPTION */}
            <input
              type="text"
              placeholder="Description"
              className="border p-3 rounded-lg"
              value={description}
              onChange={(e) =>
                setDescription(
                  e.target.value
                )
              }
              required
            />

            {/* STATUS */}
            <select
              className="border p-3 rounded-lg"
              value={status}
              onChange={(e) =>
                setStatus(
                  e.target.value
                )
              }
              required
            >

              <option value="">
                Select Status
              </option>

              <option value="SUCCESS">
                SUCCESS
              </option>

              <option value="RUNNING">
                RUNNING
              </option>

              <option value="FAILED">
                FAILED
              </option>

            </select>

            {/* BUTTON */}
            <button
              type="submit"
              className="bg-black text-white rounded-lg"
            >

              {editingId
                ? "Update"
                : "Create"}

            </button>

          </form>

        </div>

        {/* TABLE */}
        <div className="bg-white rounded-2xl shadow-lg p-6">

          <h2 className="text-2xl font-bold mb-6">
            Pipelines
          </h2>

          <table className="w-full">

            <thead>

              <tr className="border-b">

                <th className="text-left py-4">
                  Pipeline
                </th>

                <th className="text-left py-4">
                  Description
                </th>

                <th className="text-left py-4">
                  Status
                </th>

                <th className="text-left py-4">
                  Actions
                </th>

              </tr>

            </thead>

            <tbody>

              {pipelines.map((pipeline) => (

                <tr
                  key={pipeline.id}
                  className="border-b"
                >

                  <td className="py-4">
                    {pipeline.name}
                  </td>

                  <td className="py-4">
                    {pipeline.description}
                  </td>

                  <td className="py-4">

                    <span
                      className={`px-4 py-1 rounded-full text-sm font-semibold

                      ${
                        pipeline.status === "SUCCESS"
                          ? "bg-green-100 text-green-700"

                          : pipeline.status === "FAILED"
                          ? "bg-red-100 text-red-700"

                          : "bg-yellow-100 text-yellow-700"
                      }
                    `}
                    >
                      {pipeline.status}
                    </span>

                  </td>

                  {/* ACTIONS */}
                  <td className="py-4 flex gap-2">

                    {/* EDIT */}
                    <button
                      onClick={() =>
                        editPipeline(pipeline)
                      }
                      className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-lg"
                    >
                      Edit
                    </button>

                    {/* DELETE */}
                    <button
                      onClick={() =>
                        deletePipeline(
                          pipeline.id
                        )
                      }
                      className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-lg"
                    >
                      Delete
                    </button>

                  </td>

                </tr>
              ))}

            </tbody>

          </table>

        </div>

      </div>

    </div>
  );
}

export default Dashboard;