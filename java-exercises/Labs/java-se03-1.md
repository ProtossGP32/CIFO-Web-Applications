Lab#SE03-2: Library/Book, Sprint Zero
================
albertprofe
Tuesday, June 1, 2021

- <a href="#sprint-zero-jobs" id="toc-sprint-zero-jobs"><span
  class="toc-section-number">1</span> Sprint Zero jobs</a>
  - <a href="#onion-architecture" id="toc-onion-architecture"><span
    class="toc-section-number">1.1</span> Onion Architecture</a>
  - <a href="#mock-up" id="toc-mock-up"><span
    class="toc-section-number">1.2</span> Mock-up</a>
  - <a href="#uuid" id="toc-uuid"><span
    class="toc-section-number">1.3</span> UUID</a>

<div class="labs">

📘 **Linux Lab#SE03-2: Library/Book Sprint Zero**

Before define what a
<a href="https://www.bmc.com/blogs/sprint-zero/" class="external">Sprint
Zero really is</a>, let’s see what it isn’t.

- A Sprint Zero is not the phase in which the team is put together. In
  order to conduct a Sprint in the first place, a team must already be
  in place.
- A Sprint Zero is not the phase for setting up infrastructure which
  should already be implemented or easily implemented on demand, but not
  as part of a Sprint Zero.
- A Sprint Zero should not involve adding products to a backlog or
  Consider Planning as classical project with Gantt Diagrams plannings.

The main goal of a Sprint Zero is **to deliver some usable value that
can be built upon by the next team**. Sprint Zeros are required to:

- Create the project’s `skeleton`, including research spikes.
- `Keep design minimal`.
- Develop a small number of `stories` to completion.
- Be low velocity and lightweight.

More specifically, the deliverables of a Sprint Zero should be as
follows:

- A `usable` piece of code, however small.
- A `minimal` environment for writing code.
- A `prioritization` of features or a list of stories.
- A `release plan` assigning each story to a Sprint.
- A plan for the most likely implementation of `features`.

Let’s take the example of the Library Lab (SE03) to define a graph with
the requirements for the Sprint Zero deriverables:

<div>

<p>

<img src="java-se03-1_files/figure-commonmark/dot-figure-1.png"
style="width:7in;height:5in" data-fig-pos="H" />

</p>

</div>

</div>

------------------------------------------------------------------------

# Sprint Zero jobs

## Onion Architecture

<figure>
<img src="../../images/javase/onion_architecture.jpg"
style="width:40.0%" alt="Onion Architecture" />
<figcaption aria-hidden="true">Onion Architecture</figcaption>
</figure>

The layers are of <a href="" class="external">Onion Architecture</a> and
sublayers are:

- `Infrastructure`, where our database, file system, or any external web
  service we depend on live.
- `Tests`: unit, integration, end-to-end. How we validate our business
  cases.
- `User Interface`, how our users interact with the code we have
  built. - Application Services layer (sometimes known as the
  Transport/Access Layer).
- `Domain Services` layer. In this layer is where the majority of our
  business logic lives, it carries out the operations to turn A into B,
  input into output, egg into chicken.
- The core layer, the `Domain Model` layer which is the representation
  of the high level data objects we use.

## Mock-up

``` js
Options:
1- Make a Borrow
2- User Management
3- Book Management
4- Quit
Option?
'Borrow'
User?
'159742OPI'
Book?
'Anna Karenina'
Proceed, are your sure?
'Yes'
Borrow made, borrowId: RER7567, dueDate: 15/02/2023 
Options:
1- Make a Borrow
2- User Management
3- Book Management
4- Quit
Option?
'Quit'
```

## UUID

- <a href="https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html"
  class="external">Class UUID</a>

<!--- navLinks -->
<br><br>

<div class="row">

<br>

<div class="column left previous">

<br> [{{< fa solid arrow-left >}} Lab#SE02-4: Movie/Review,
interactivity and coupling](/javase/selab2-4.qmd) <br>

</div>

<br>

<div class="column center">

<br> [{{< fa solid arrow-up >}} top](#top) <br>

</div>

<br>

<div class="column right next">

<br> [Java SE Resources
{{< fa solid arrow-right >}}](/javase/resources.qmd) <br>

</div>

<br>

</div>
