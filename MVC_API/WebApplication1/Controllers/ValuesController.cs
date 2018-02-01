using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using AdoManager;
using Dapper;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    
    public class ValuesController : ApiController
    {
        [System.Web.Http.HttpPost]
        [System.Web.Http.Route("api/SaveEmployee")]
        public IHttpActionResult SaveEmployee(Employee employee)
        {
            var info = Connections.conn.SqlConn.Query("InsertEmployee",
                new {employee.FirstName,employee.LastName},
                commandType: System.Data.CommandType.StoredProcedure);
            return Ok(info.FirstOrDefault());
        }
    }
}
