import React, { useCallback, useEffect, useState } from "react";
import CinemaAxios from "../../apis/CinemaAxios";
import { useNavigate } from "react-router-dom";
import{jwtDecode} from 'jwt-decode'
import {Form, Button, Col, Row, Table } from "react-bootstrap";

const Linija=()=>{

    const token=window.localStorage.getItem("jwt")
    const decoded=token?jwtDecode(token):null
    const isAdmin=decoded?.role?.authority==="ROLE_ADMIN"

    const [pageNo,setPageNo]=useState(0);
    const [pageMax,setPageMax]=useState(0);

    const [linije,setLinije]=useState([])

    const [prevoznici,setPrevoznici]=useState([])
    const [novaLinija,setNovaLinija]=useState({
        brojMesta:null,
        cenaKarte:null,
        destinacija:"",
        vremePolaska:null,
        prevoznikId:null
    })

    const [searchParams,setSearchParams]=useState([])



    const navigate=useNavigate();


    const getLinije=useCallback(()=>{
        CinemaAxios.get(`/linije?pageNo=${pageNo}`,{
            params:{
                destinacija:searchParams.destinacija,
                prevoznikId:searchParams.prevoznikId,
                cenaKarteDo:searchParams.cenaKarte             
            }
        })
            .then(res=>{
                console.log(res)
                setLinije(res.data)
                setPageMax(res.headers['total-pages'])
                
            })
            .catch(error=>{
                alert("Neuspesno dobavljanje podataka !")
            })
    },[pageNo,searchParams])

    useEffect(()=>{
        getLinije()
        getPrevoznici()
    },[pageNo])

    const goEdit=(linijaId)=>{
        navigate("/linije/edit/"+linijaId)
    }

    const obrisiLiniju=(linijaId)=>{
        CinemaAxios.delete("/linije/"+linijaId)
            .then((res)=>{
                console.log(res)
                window.location.reload()
            })
            .catch((error)=>{
                console.log(error)
            })
    }

    const rezervisi=(linijaId)=>{
       navigate("/linije/rezervacije/"+linijaId)
    }

   


    const renderLinije=()=>{
        return linije.map((linija)=>{
            return(
                <tr key={linija.id}>
                    <td>{linija.prevoznikNaziv}</td>
                    <td>{linija.destinacija}</td>
                    <td>{linija.brojMesta}</td>
                    <td>{linija.vremePolaska}</td>
                    <td>{linija.cenaKarte+" din."}</td>
                    <td><Button disabled={linija.brojMesta==0} onClick={()=>rezervisi(linija.id)}>Rezervisi </Button></td>
                    <td> {isAdmin && <Button variant="warning" onClick={()=>goEdit(linija.id)}> Izmeni </Button>}</td>
                    <td> {isAdmin && <Button  variant="danger" onClick={()=>obrisiLiniju(linija.id)}> Obrisi </Button>}</td>
              
                </tr>

                  
            )      
        })
    }


    const getPrevoznici=useCallback(()=>{
        CinemaAxios.get("/prevoznici")
            .then(res=>{
                console.log(res)
                setPrevoznici(res.data)
                
            })
            .catch(error=>{
                alert("Neuspesno dobavljanje podataka !")
            })
    },[])
    
    
    


    


    const inputValueChanged=(e)=>{
        const {name,value}=e.target;
        setNovaLinija({...novaLinija,[name]:value})
       // console.log({name:value})
    }

    const dodajLiniju=()=>{
        CinemaAxios.post("/linije",{
            brojMesta:novaLinija.brojMesta,
            cenaKarte:novaLinija.cenaKarte,
            destinacija:novaLinija.destinacija,
            vremePolaska:novaLinija.vremePolaska,
            prevoznikId:novaLinija.prevoznikId


        })
            .then((res)=>{
                console.log(res.data)
                window.location.reload()
                
            })
            .catch((error)=>{
                alert("Neuspesno dodavanje !")
            })
        
    }

    const changeHandler=(e)=>{
        const{name,value}=e.target;
        setSearchParams({...searchParams,[name]:value})
    }

    const searchHandler=()=>{
        getLinije()
    }

    return(
       
        <Row>
        
        <Row>
            <Row><h1>Autobuska stanica Subotica</h1></Row>
                <Col > 
                    <br></br>
                    <h3>Dodavanje linija</h3>
               
                    <Form>

                    <Form.Group>
                        <br/>
                        <Form.Label>Broj mesta</Form.Label>
                        <Form.Control id="brojMesta" type="number" name="brojMesta" onChange={inputValueChanged}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Cena karte</Form.Label>
                        <Form.Control id="cenaKarte" type="number" name="cenaKarte" onChange={inputValueChanged}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Destinacija</Form.Label>
                        <Form.Control id="destinacija" name="destinacija" onChange={inputValueChanged}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Vreme polaska</Form.Label>
                        <Form.Control id="vremePolaska" type="time" name="vremePolaska" onChange={inputValueChanged}></Form.Control>
                    </Form.Group>


                    <Form.Group>
                    <Form.Label>Prevoznik</Form.Label>
                    <Form.Control id="stanje" as='select' name="prevoznikId" onChange={inputValueChanged}>
                        <option></option>
                        {
                            prevoznici.map((prevoznik)=>{
                                return <option key={prevoznik.id} value={prevoznik.id} >{prevoznik.naziv}</option>
                            })
                        }
                    </Form.Control>
                    </Form.Group>

                    <br/>
                    <Button onClick={()=>dodajLiniju()}>Dodaj</Button> 
                     <br/>
                </Form>
                </Col>
                </Row>


                <Row>
                    <Col>

                    <Form>
                    <br></br>
                        <h3>Pretraga linija</h3>
                        
                        <Form.Group><br/>
                            <Form.Label> Destinacija</Form.Label>
                            <Form.Control  name="destinacija" onChange={changeHandler} />                           
                               
                        </Form.Group>

                        <Form.Group>
                            <Form.Label> Prevoznik </Form.Label>
                            <Form.Control as='select' name="prevoznikId"  onChange={changeHandler}>
                                <option ></option>
                                {
                                prevoznici.map((prevoznik)=>{
                                return <option key={prevoznik.id} value={prevoznik.id} >{prevoznik.naziv}</option>
                                    })
                                 }
                            </Form.Control>
                            
                        </Form.Group>
                    
                        <Form.Group>
                            <Form.Label> Maksimalna cena </Form.Label>
                            <Form.Control type='number' name="cenaKarte"  onChange={changeHandler}/>
                               
                            <br></br>
                        </Form.Group>

                        <Button onClick={searchHandler} >Pretrazi</Button>
                            
                        
                    </Form>

                    </Col>
                </Row>
                
                <Row>
                <Col>
               
                    <div style={{ display: 'flex', justifyContent: "flex-end" }}>
                        <span>
                            <Button disabled={pageNo<=0} onClick={(e)=>setPageNo(pageNo-1)}> Previous</Button>
                            <Button disabled={pageNo>=pageMax-1} onClick={(e)=>setPageNo(pageNo+1)}> Next </Button>
                            
                        
                        </span>
                    </div>

                </Col>
                </Row>
                        
                <Row> 
                <Col>
                
                    <Table className="table-striped table-bordered table-hover">

                        <thead className="table-dark">
                            <tr>
                                <th> Naziv prevoznika  </th>
                                <th> Destinacija </th>
                                <th> Broj mesta </th>
                                <th> Vreme polaska </th>
                                <th> Cena karte </th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                          {renderLinije()}
                        </tbody>
                    </Table>

                </Col>
                </Row>
            
        </Row>
       
    )

}

export default Linija;

//onChange={handleChange}   <Button onClick={()=>handleSearch()}>Pretrazi</Button>