import Main from "./components/Main";
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Form from "./components/Form";
import Navbar from './components/Navbar';
import { useEffect, useState } from "react";
import { openUserSession } from "./services/weatherAPIService";

function App() {
  const [flag, setFlag] = useState(false);
  useEffect(()=> {
    openUserSession().then((res) =>{
      setFlag(true);
    });
}, [])

  return (
    <>
    <><Navbar/>
      <BrowserRouter>
        <Routes>
            <Route path = "/">
                <Route index element = {<Form/>}/>
                <Route path = 'weather' element = {<>{flag && <Main/>}</>}/>
            </Route>
        </Routes>
      </BrowserRouter></>
    </>
  );
}

export default App;