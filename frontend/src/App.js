import Main from "./components/Main";
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Form from "./components/Form";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
            <Route path = "/">
                <Route index element = {<Form/>}/>
                <Route path = 'weather' element = {<Main/>}/>
            </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
